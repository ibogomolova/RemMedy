package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.NotificationSettingDto;
import com.remmedy.pharma_box.entity.NotificationSetting;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationSettingMapper {
    NotificationSettingDto toDto(NotificationSetting setting);
}
