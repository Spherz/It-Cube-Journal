package com.itcube.journal.service;

import com.itcube.journal.model.Staff;
import com.itcube.journal.repos.StaffRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepo staffRepo;

    public Iterable<Staff> findAll() {
        log.info("Find all staff");
        return staffRepo.findAll();
    }

    public Staff save(Staff staff) {
        log.info("Save staff: {}", staff.getId());
        return staffRepo.save(staff);
    }
}
