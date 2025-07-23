package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.NotificationSettingDto;
import com.remmedy.pharma_box.entity.NotificationSetting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationSettingMapper {
//    @Mapping(source = "")
    NotificationSettingDto toDto(NotificationSetting setting);
}
