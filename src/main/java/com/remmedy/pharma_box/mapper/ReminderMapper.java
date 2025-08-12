package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.ReminderDto;
import com.remmedy.pharma_box.entity.Reminder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReminderMapper {

    @Mapping(source = "medicine.id", target = "medicineId")
    @Mapping(source = "user.id", target = "userId")
    ReminderDto toDto(Reminder reminder);

    @Mapping(source = "medicineId", target = "medicine.id")
    @Mapping(source = "userId", target = "user.id")
    Reminder toEntity(ReminderDto dto);
}
