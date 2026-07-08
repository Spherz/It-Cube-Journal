package com.itcube.journal.mapper.schedule;

import com.itcube.journal.dto.schedule.ScheduleDTO;
import com.itcube.journal.dto.schedule.ScheduleExceptionDTO;
import com.itcube.journal.dto.schedule.ScheduleRequestDTO;
import com.itcube.journal.dto.schedule.ScheduleResponseDTO;
import com.itcube.journal.model.Schedule;
import com.itcube.journal.model.ScheduleException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(target = "group", ignore = true)
    @Mapping(target = "exceptions", ignore = true)
    Schedule toEntity(ScheduleRequestDTO scheduleRequestDTO);

    @Mapping(target = "groupId", expression = "java(schedule.getGroup() != null ? schedule.getGroup().getId() : null)")
    ScheduleResponseDTO toResponseDTO(Schedule schedule);

    ScheduleDTO toDTO(Schedule schedule);

    ScheduleExceptionDTO toDTO(ScheduleException scheduleException);

    @Mapping(target = "group", ignore = true)
    @Mapping(target = "exceptions", ignore = true)
    void updateFromDTO(ScheduleRequestDTO scheduleRequestDTO, @MappingTarget Schedule schedule);
}
