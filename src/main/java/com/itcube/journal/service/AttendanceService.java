package com.itcube.journal.service;

import com.itcube.journal.dto.attendance.AttendanceMarkRequestDTO;
import com.itcube.journal.dto.attendance.AttendanceRequestDTO;
import com.itcube.journal.dto.attendance.AttendanceResponseDTO;
import com.itcube.journal.dto.journal.JournalResponseDTO;

import java.util.List;

public interface AttendanceService {

    List<AttendanceResponseDTO> findAllAttendances();

    AttendanceResponseDTO findAttendanceById(Long id);

    List<AttendanceResponseDTO> findAttendancesByStudentId(Long studentId);

    List<AttendanceResponseDTO> findAttendancesByGroupId(Long groupId);

    AttendanceResponseDTO createAttendance(AttendanceRequestDTO attendanceRequestDTO);

    AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO attendanceRequestDTO);

    void deleteAttendance(Long id);

    AttendanceResponseDTO setMark(Long studentId, AttendanceMarkRequestDTO attendanceMarkRequestDTO);

    JournalResponseDTO getGroupJournal(Long groupId);
}
