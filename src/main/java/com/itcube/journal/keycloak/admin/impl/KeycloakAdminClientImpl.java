package com.itcube.journal.keycloak.admin.impl;

import com.itcube.journal.config.KeycloakProperties;
import com.itcube.journal.dto.auth.TokenResponse;
import com.itcube.journal.dto.teacher.TeacherDTO;
import com.itcube.journal.keycloak.admin.KeycloakAdminClient;
import com.itcube.journal.keycloak.support.KeycloakRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakAdminClientImpl implements KeycloakAdminClient {

    private final RestClient restClient;
    private final KeycloakRequestFactory keycloakRequestFactory;
    private final KeycloakProperties keycloakProperties;

    private volatile String cachedAccessToken;
    private volatile Instant tokenExpiresAt = Instant.EPOCH;

    @Override
    public List<TeacherDTO> findUsersWithRealmRole(String roleName) {
        KeycloakUserRepresentation[] users = restClient.get()
                .uri(adminRealmPath() + "/roles/{roleName}/users", roleName)
                .header("Authorization", "Bearer " + accessToken())
                .retrieve()
                .body(KeycloakUserRepresentation[].class);

        if (users == null) {
            return List.of();
        }

        return List.of(users).stream().map(this::toTeacherDTO).toList();
    }

    @Override
    public Optional<TeacherDTO> findUserById(String userId) {
        try {
            KeycloakUserRepresentation user = restClient.get()
                    .uri(adminRealmPath() + "/users/{id}", userId)
                    .header("Authorization", "Bearer " + accessToken())
                    .retrieve()
                    .body(KeycloakUserRepresentation.class);

            return Optional.ofNullable(user).map(this::toTeacherDTO);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean userHasRealmRole(String userId, String roleName) {
        try {
            KeycloakRoleRepresentation[] roles = restClient.get()
                    .uri(adminRealmPath() + "/users/{id}/role-mappings/realm/composite", userId)
                    .header("Authorization", "Bearer " + accessToken())
                    .retrieve()
                    .body(KeycloakRoleRepresentation[].class);

            if (roles == null) {
                return false;
            }

            return List.of(roles).stream().anyMatch(role -> roleName.equals(role.name()));
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

    private synchronized String accessToken() {
        if (cachedAccessToken == null || Instant.now().isAfter(tokenExpiresAt)) {
            TokenResponse tokenResponse = restClient.post()
                    .uri(keycloakRequestFactory.tokenEndpoint())
                    .body(keycloakRequestFactory.buildClientCredentialsForm())
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .retrieve()
                    .toEntity(TokenResponse.class)
                    .getBody();

            cachedAccessToken = tokenResponse.access_token();
            tokenExpiresAt = Instant.now().plusSeconds(tokenResponse.expires_in() - 10L);
        }

        return cachedAccessToken;
    }

    private String adminRealmPath() {
        return "/admin/realms/" + keycloakProperties.getRealm();
    }

    private TeacherDTO toTeacherDTO(KeycloakUserRepresentation user) {
        return new TeacherDTO(
                user.id(),
                user.username(),
                user.email(),
                user.firstName(),
                user.lastName(),
                firstAttribute(user.attributes(), "patronymic"),
                firstAttribute(user.attributes(), "birthDate"),
                firstAttribute(user.attributes(), "education"),
                firstAttribute(user.attributes(), "qualification"),
                asInteger(firstAttribute(user.attributes(), "diploma_number"))
        );
    }

    private String firstAttribute(Map<String, List<String>> attributes, String key) {
        if (attributes == null) {
            return null;
        }
        List<String> values = attributes.get(key);
        return values == null || values.isEmpty() ? null : values.get(0);
    }

    private Integer asInteger(String value) {
        return value == null ? null : Integer.valueOf(value);
    }

    private record KeycloakUserRepresentation(String id, String username, String email, String firstName,
                                               String lastName, Map<String, List<String>> attributes) {
    }

    private record KeycloakRoleRepresentation(String id, String name) {
    }
}
