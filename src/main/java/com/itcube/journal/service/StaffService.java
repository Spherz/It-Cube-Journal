package com.itcube.journal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcube.journal.dto.staff.StaffRequestDTO;
import com.itcube.journal.mapper.staff.StaffMapper;
import com.itcube.journal.model.Staff;
import com.itcube.journal.repos.StaffRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepo staffRepo;
    private final StaffMapper staffMapper;
    private final ObjectMapper objectMapper;

    public Staff findById(Integer id) {
        return staffRepo.findById(id).orElse(null);
    }

    public Iterable<Staff> findAll() {
        log.info("Find all staff");
        return staffRepo.findAll();
    }

    public Staff save(Staff staff) {
        log.info("Save staff: {}", staff.getId());
        return staffRepo.save(staff);
    }

    @Transactional
    public Staff update(StaffRequestDTO staffRequestDTO, Integer staffId) {
        Staff updatedStaff = staffRepo.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Unable to find staff with id: " + staffId));

        staffMapper.updateStaffFromDTO(staffRequestDTO, updatedStaff);

        return staffRepo.save(updatedStaff);
    }
}
