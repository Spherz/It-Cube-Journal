package com.itcube.journal.keycloak.admin;

import com.itcube.journal.dto.teacher.TeacherDTO;

import java.util.List;
import java.util.Optional;

public interface KeycloakAdminClient {

    List<TeacherDTO> findUsersWithRealmRole(String roleName);

    Optional<TeacherDTO> findUserById(String userId);

    boolean userHasRealmRole(String userId, String roleName);
}
