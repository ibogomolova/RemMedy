package com.remmedy.pharma_box.service;

import com.remmedy.pharma_box.dto.CreateNotificationSettingDto;
import com.remmedy.pharma_box.dto.NotificationSettingDto;
import com.remmedy.pharma_box.entity.NotificationSetting;

public interface NotificationSettingService {

    NotificationSettingDto createOrUpdate(CreateNotificationSettingDto dto);

    NotificationSettingDto toDto(NotificationSetting setting);
}
