package com.itcube.journal.controller;

import com.itcube.journal.dto.attendance.AttendanceMarkRequestDTO;
import com.itcube.journal.dto.attendance.AttendanceRequestDTO;
import com.itcube.journal.dto.attendance.AttendanceResponseDTO;
import com.itcube.journal.dto.journal.JournalResponseDTO;
import com.itcube.journal.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<List<AttendanceResponseDTO>> getAllAttendances() {
        return ResponseEntity.ok(attendanceService.findAllAttendances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> getAttendanceById(@PathVariable Long id) {
        return ResponseEntity.ok(attendanceService.findAttendanceById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendancesByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.findAttendancesByStudentId(studentId));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendancesByGroupId(@PathVariable Long groupId) {
        return ResponseEntity.ok(attendanceService.findAttendancesByGroupId(groupId));
    }

    @GetMapping("/group/{groupId}/journal")
    public ResponseEntity<JournalResponseDTO> getGroupJournal(@PathVariable Long groupId) {
        return ResponseEntity.ok(attendanceService.getGroupJournal(groupId));
    }

    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> createAttendance(
            @RequestBody AttendanceRequestDTO attendanceRequestDTO) {
        return ResponseEntity.ok(attendanceService.createAttendance(attendanceRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> updateAttendance(
            @PathVariable Long id,
            @RequestBody AttendanceRequestDTO attendanceRequestDTO) {
        return ResponseEntity.ok(attendanceService.updateAttendance(id, attendanceRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/student/{studentId}/mark")
    public ResponseEntity<AttendanceResponseDTO> setMark(
            @PathVariable Long studentId,
            @RequestBody AttendanceMarkRequestDTO attendanceMarkRequestDTO) {
        return ResponseEntity.ok(attendanceService.setMark(studentId, attendanceMarkRequestDTO));
    }
}
