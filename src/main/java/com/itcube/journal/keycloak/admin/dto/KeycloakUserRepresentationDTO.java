package com.itcube.journal.keycloak.admin.dto;

import java.util.List;
import java.util.Map;

public record KeycloakUserRepresentationDTO(String id, String username, String email, String firstName,
                                              String lastName, Map<String, List<String>> attributes) {
}
