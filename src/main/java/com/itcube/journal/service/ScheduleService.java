package com.itcube.journal.service;

import com.itcube.journal.model.Schedule;
import com.itcube.journal.repos.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;

    public List<Schedule> getAll() {
        return scheduleRepo.findAll();
    }
}
