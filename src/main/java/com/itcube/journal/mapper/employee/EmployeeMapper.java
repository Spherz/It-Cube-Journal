package com.itcube.journal.mapper.employee;

import com.itcube.journal.dto.staff.EmployeeRequestDTO;
import com.itcube.journal.dto.staff.EmployeeResponseDTO;
import com.itcube.journal.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEntity(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO toResponseDTO(Employee employee);

    void updateFromDTO(EmployeeRequestDTO employeeRequestDTO, @MappingTarget Employee employee);
}
