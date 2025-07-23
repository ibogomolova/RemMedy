package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.ReminderDto;
import com.remmedy.pharma_box.entity.Reminder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReminderMapper {

    @Mapping(source = "medicine.id", target = "medicineId")
    ReminderDto toDto(Reminder reminder);
}
