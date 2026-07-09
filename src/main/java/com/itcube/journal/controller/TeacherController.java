package com.itcube.journal.controller;

import com.itcube.journal.dto.teacher.TeacherDTO;
import com.itcube.journal.enums.Role;
import com.itcube.journal.keycloak.admin.KeycloakAdminClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private final KeycloakAdminClient keycloakAdminClient;

    @GetMapping
    @PreAuthorize("hasAnyRole('methodist', 'admin')")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(keycloakAdminClient.findUsersWithRealmRole(Role.TEACHER.name()));
    }
}
