package com.remmedy.pharma_box.service.impl;

import com.remmedy.pharma_box.dto.CreateNotificationSettingDto;
import com.remmedy.pharma_box.dto.NotificationSettingDto;
import com.remmedy.pharma_box.entity.NotificationSetting;
import com.remmedy.pharma_box.entity.User;
import com.remmedy.pharma_box.exception.UserNotFoundException;
import com.remmedy.pharma_box.repository.NotificationSettingRepository;
import com.remmedy.pharma_box.repository.UserRepository;
import com.remmedy.pharma_box.service.NotificationSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationSettingServiceImpl implements NotificationSettingService {

    private final NotificationSettingRepository settingsRepository;
    private final UserRepository userRepository;

    @Override
    public NotificationSettingDto createOrUpdate(CreateNotificationSettingDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        NotificationSetting setting = settingsRepository.findByUser(user)
                .orElse(new NotificationSetting());

        setting.setUser(user);
        setting.setNotifyByEmail(dto.isNotifyByEmail());
        setting.setNotifyByPush(dto.isNotifyByPush());
        setting.setDaysBefore(dto.getDaysBefore());
        settingsRepository.save(setting);

        return toDto(setting);
    }

    @Override
    public NotificationSettingDto toDto(NotificationSetting setting) {
        NotificationSettingDto dto = new NotificationSettingDto();
        dto.setId(setting.getId());
        dto.setNotifyByEmail(setting.isNotifyByEmail());
        dto.setNotifyByPush(setting.isNotifyByPush());
        dto.setDaysBefore(setting.getDaysBefore());
        return dto;
    }
}
