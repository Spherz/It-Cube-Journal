package com.itcube.journal.keycloak.admin.impl;

import com.itcube.journal.dto.auth.TokenResponse;
import com.itcube.journal.dto.teacher.TeacherDTO;
import com.itcube.journal.keycloak.admin.KeycloakAdminClient;
import com.itcube.journal.keycloak.admin.dto.KeycloakRoleRepresentationDTO;
import com.itcube.journal.keycloak.admin.dto.KeycloakUserRepresentationDTO;
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

    private volatile String cachedAccessToken;
    private volatile Instant tokenExpiresAt = Instant.EPOCH;

    @Override
    public List<TeacherDTO> findUsersWithRealmRole(String roleName) {
        KeycloakUserRepresentationDTO[] users = restClient.get()
                .uri(keycloakRequestFactory.adminUsersByRoleEndpoint(), roleName)
                .header("Authorization", "Bearer " + accessToken())
                .retrieve()
                .body(KeycloakUserRepresentationDTO[].class);

        if (users == null) {
            return List.of();
        }

        return List.of(users).stream().map(this::toTeacherDTO).toList();
    }

    @Override
    public Optional<TeacherDTO> findUserById(String userId) {
        try {
            KeycloakUserRepresentationDTO user = restClient.get()
                    .uri(keycloakRequestFactory.adminUserByIdEndpoint(), userId)
                    .header("Authorization", "Bearer " + accessToken())
                    .retrieve()
                    .body(KeycloakUserRepresentationDTO.class);

            return Optional.ofNullable(user).map(this::toTeacherDTO);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean userHasRealmRole(String userId, String roleName) {
        try {
            KeycloakRoleRepresentationDTO[] roles = restClient.get()
                    .uri(keycloakRequestFactory.adminUserRoleMappingsEndpoint(), userId)
                    .header("Authorization", "Bearer " + accessToken())
                    .retrieve()
                    .body(KeycloakRoleRepresentationDTO[].class);

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

    private TeacherDTO toTeacherDTO(KeycloakUserRepresentationDTO user) {
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
}
