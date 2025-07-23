package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.CreateNotificationSettingDto;
import com.remmedy.pharma_box.dto.NotificationSettingDto;
import com.remmedy.pharma_box.entity.NotificationSetting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationSettingMapper {

    @Mapping(source = "user.id", target = "userId")
    NotificationSettingDto toDto(NotificationSetting setting);

    @Mapping(source = "userId", target = "user.id")
    NotificationSetting toEntity(CreateNotificationSettingDto dto);
}
