package com.itcube.journal.service.impl;

import com.itcube.journal.dto.attendance.AttendanceMarkRequestDTO;
import com.itcube.journal.dto.attendance.AttendanceRequestDTO;
import com.itcube.journal.dto.attendance.AttendanceResponseDTO;
import com.itcube.journal.dto.journal.JournalResponseDTO;
import com.itcube.journal.dto.student.StudentResponseDTO;
import com.itcube.journal.exceptions.AttendanceNotFoundException;
import com.itcube.journal.exceptions.GroupNotFoundException;
import com.itcube.journal.exceptions.StudentNotFoundException;
import com.itcube.journal.exceptions.StudentNotInGroupException;
import com.itcube.journal.mapper.attendance.AttendanceMapper;
import com.itcube.journal.model.Attendance;
import com.itcube.journal.model.Group;
import com.itcube.journal.model.Student;
import com.itcube.journal.repository.AttendanceRepository;
import com.itcube.journal.repository.GroupRepository;
import com.itcube.journal.repository.StudentRepository;
import com.itcube.journal.service.AttendanceService;
import com.itcube.journal.service.ScheduleService;
import com.itcube.journal.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceMapper attendanceMapper;
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final ScheduleService scheduleService;
    private final StudentService studentService;

    @Override
    public List<AttendanceResponseDTO> findAllAttendances() {
        return attendanceRepository.findAll().stream()
                .map(attendanceMapper::toResponseDTO)
                .toList();
    }

    @Override
    public AttendanceResponseDTO findAttendanceById(Long id) {
        Attendance attendance = getAttendanceOrThrow(id);

        return attendanceMapper.toResponseDTO(attendance);
    }

    @Override
    public List<AttendanceResponseDTO> findAttendancesByStudentId(Long studentId) {
        return attendanceRepository.findByStudentsId(studentId).stream()
                .map(attendanceMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<AttendanceResponseDTO> findAttendancesByGroupId(Long groupId) {
        return attendanceRepository.findByGroupsId(groupId).stream()
                .map(attendanceMapper::toResponseDTO)
                .toList();
    }

    @Override
    public AttendanceResponseDTO createAttendance(AttendanceRequestDTO attendanceRequestDTO) {
        Attendance attendance = attendanceMapper.toEntity(attendanceRequestDTO);
        attendance.setStudents(resolveStudent(attendanceRequestDTO.studentId()));
        attendance.setGroups(resolveGroup(attendanceRequestDTO.groupId()));

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return attendanceMapper.toResponseDTO(savedAttendance);
    }

    @Override
    public AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO attendanceRequestDTO) {
        Attendance attendance = getAttendanceOrThrow(id);

        attendanceMapper.updateFromDTO(attendanceRequestDTO, attendance);
        attendance.setStudents(resolveStudent(attendanceRequestDTO.studentId()));
        attendance.setGroups(resolveGroup(attendanceRequestDTO.groupId()));

        Attendance updatedAttendance = attendanceRepository.save(attendance);

        return attendanceMapper.toResponseDTO(updatedAttendance);
    }

    @Override
    public void deleteAttendance(Long id) {
        Attendance attendance = getAttendanceOrThrow(id);

        attendanceRepository.delete(attendance);
    }

    @Override
    public AttendanceResponseDTO setMark(Long studentId, AttendanceMarkRequestDTO attendanceMarkRequestDTO) {
        Student student = resolveStudent(studentId);

        if (student.getGroup() == null) {
            throw new StudentNotInGroupException("Student with id: " + studentId + " is not assigned to a group");
        }

        Attendance attendance = attendanceRepository
                .findByStudentsIdAndAttendanceDate(studentId, attendanceMarkRequestDTO.attendanceDate())
                .orElseGet(Attendance::new);

        attendance.setStudents(student);
        attendance.setGroups(student.getGroup());
        attendance.setAttendanceDate(attendanceMarkRequestDTO.attendanceDate());
        attendance.setMark(attendanceMarkRequestDTO.mark());

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return attendanceMapper.toResponseDTO(savedAttendance);
    }

    @Override
    public JournalResponseDTO getGroupJournal(Long groupId) {
        Group group = resolveGroup(groupId);

        List<LocalDate> lessonDates = scheduleService.getLessonDatesByGroupId(groupId);
        List<StudentResponseDTO> students = studentService.findStudentsByGroupId(groupId);

        return new JournalResponseDTO(group.getId(), group.getName(), lessonDates, students);
    }

    private Attendance getAttendanceOrThrow(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException("Unable to find attendance with id: " + id));
    }

    private Student resolveStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Unable to find student with id: " + studentId));
    }

    private Group resolveGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Unable to find group with id: " + groupId));
    }
}
