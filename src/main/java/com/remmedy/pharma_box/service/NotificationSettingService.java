package com.remmedy.pharma_box.service;

import com.remmedy.pharma_box.dto.notification.CreateNotificationSettingDto;
import com.remmedy.pharma_box.dto.notification.NotificationSettingDto;
import com.remmedy.pharma_box.model.NotificationSetting;

public interface NotificationSettingService {

    NotificationSettingDto createOrUpdate(CreateNotificationSettingDto dto);

    NotificationSettingDto toDto(NotificationSetting setting);
}
