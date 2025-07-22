package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.CreateReminderDto;
import com.remmedy.pharma_box.dto.ReminderDto;
import com.remmedy.pharma_box.entity.Reminder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReminderMapper {
    ReminderDto toDto(Reminder reminder);
    Reminder toEntity(CreateReminderDto dto);
}
