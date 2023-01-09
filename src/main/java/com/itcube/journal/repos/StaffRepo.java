package com.itcube.journal.repos;

import com.itcube.journal.domain.Staff;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StaffRepo extends CrudRepository<Staff, Long> {
    List<Staff> findAllBySurname(String surname);
}
