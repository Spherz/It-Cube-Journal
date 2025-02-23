package com.itcube.journal.mapper.staff;

import com.itcube.journal.dto.staff.StaffRequestDTO;
import com.itcube.journal.model.Staff;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper {

    public Staff updateStaffFromDTO(StaffRequestDTO staffRequestDTO, Staff staff) {
        staff.setFirstname(staffRequestDTO.getFirstName() == null ? staff.getFirstname() : staffRequestDTO.getFirstName());
        staff.setSurname(staffRequestDTO.getSurname() == null ? staff.getSurname() : staffRequestDTO.getSurname());
        staff.setSecondname(staffRequestDTO.getFatherName() == null ? staff.getSecondname() : staffRequestDTO.getFatherName());
        staff.setBirthDate(staffRequestDTO.getBirthDate() == null ? staff.getBirthDate() : staffRequestDTO.getBirthDate());
        staff.setEducation(staffRequestDTO.getEducation() == null ? staff.getEducation() : staffRequestDTO.getEducation());
        staff.setDiplomaNumber(staffRequestDTO.getDiplomaNumber() == null ? staff.getDiplomaNumber() : staffRequestDTO.getDiplomaNumber());
        staff.setQualification(staffRequestDTO.getQualification() == null ? staff.getQualification() : staffRequestDTO.getQualification());

        return staff;
    }
}
