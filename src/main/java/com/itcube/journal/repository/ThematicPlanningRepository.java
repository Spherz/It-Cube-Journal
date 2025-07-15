package com.itcube.journal.repository;

import com.itcube.journal.model.ThematicPlanning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ThematicPlanningRepository extends JpaRepository<ThematicPlanning, Long> {

}
