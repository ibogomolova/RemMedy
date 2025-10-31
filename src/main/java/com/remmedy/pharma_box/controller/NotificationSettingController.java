package com.remmedy.pharma_box.controller;

import com.remmedy.pharma_box.dto.notification.CreateNotificationSettingDto;
import com.remmedy.pharma_box.dto.notification.NotificationSettingDto;
import com.remmedy.pharma_box.service.NotificationSettingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification-settings")
@RequiredArgsConstructor
public class NotificationSettingController {

    private final NotificationSettingService settingsService;

    @PostMapping
    public NotificationSettingDto createOrUpdate(@Valid @RequestBody CreateNotificationSettingDto dto) {
        return settingsService.createOrUpdate(dto);
    }
}
