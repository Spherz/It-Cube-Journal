package com.itcube.journal.mapper.attendance;

import com.itcube.journal.dto.attendance.AttendanceDTO;
import com.itcube.journal.dto.attendance.AttendanceRequestDTO;
import com.itcube.journal.dto.attendance.AttendanceResponseDTO;
import com.itcube.journal.model.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    @Mapping(target = "students", ignore = true)
    @Mapping(target = "groups", ignore = true)
    Attendance toEntity(AttendanceRequestDTO attendanceRequestDTO);

    @Mapping(target = "studentId", expression = "java(attendance.getStudents() != null ? attendance.getStudents().getId() : null)")
    @Mapping(target = "studentFirstname", expression = "java(attendance.getStudents() != null ? attendance.getStudents().getFirstname() : null)")
    @Mapping(target = "studentSurname", expression = "java(attendance.getStudents() != null ? attendance.getStudents().getSurname() : null)")
    @Mapping(target = "groupId", expression = "java(attendance.getGroups() != null ? attendance.getGroups().getId() : null)")
    @Mapping(target = "groupName", expression = "java(attendance.getGroups() != null ? attendance.getGroups().getName() : null)")
    AttendanceResponseDTO toResponseDTO(Attendance attendance);

    @Mapping(target = "studentId", expression = "java(attendance.getStudents() != null ? attendance.getStudents().getId() : null)")
    @Mapping(target = "groupId", expression = "java(attendance.getGroups() != null ? attendance.getGroups().getId() : null)")
    AttendanceDTO toDTO(Attendance attendance);

    @Mapping(target = "students", ignore = true)
    @Mapping(target = "groups", ignore = true)
    void updateFromDTO(AttendanceRequestDTO attendanceRequestDTO, @MappingTarget Attendance attendance);
}
