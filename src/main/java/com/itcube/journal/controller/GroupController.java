package com.itcube.journal.controller;

import com.itcube.journal.dto.groups.AssignTeacherRequestDTO;
import com.itcube.journal.dto.groups.GroupRequestDTO;
import com.itcube.journal.dto.groups.GroupResponseDTO;
import com.itcube.journal.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupResponseDTO>> getAllGroups() {
        return ResponseEntity.ok(groupService.findAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.findGroupById(id));
    }

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<List<GroupResponseDTO>> getMyGroups(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(groupService.findGroupsByEmployeeSub(jwt.getSubject()));
    }

    @PostMapping
    public ResponseEntity<GroupResponseDTO> createGroup(
            @RequestBody GroupRequestDTO groupRequestDTO) {
        return ResponseEntity.ok(groupService.createGroup(groupRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> updateGroup(
            @PathVariable Long id,
            @RequestBody GroupRequestDTO groupRequestDTO) {
        return ResponseEntity.ok(groupService.updateGroup(id, groupRequestDTO));
    }

    @PatchMapping("/{id}/teacher")
    @PreAuthorize("hasAuthority('GROUP_TEACHER_ASSIGN')")
    public ResponseEntity<GroupResponseDTO> assignTeacher(
            @PathVariable Long id,
            @RequestBody AssignTeacherRequestDTO assignTeacherRequestDTO) {
        return ResponseEntity.ok(groupService.assignTeacher(id, assignTeacherRequestDTO.employeeSub()));
    }
}
