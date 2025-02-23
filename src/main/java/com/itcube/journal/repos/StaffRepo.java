package com.itcube.journal.repos;

import com.itcube.journal.model.Staff;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StaffRepo extends CrudRepository<Staff, Integer> {
    List<Staff> findAllBySurname(String surname);
}
