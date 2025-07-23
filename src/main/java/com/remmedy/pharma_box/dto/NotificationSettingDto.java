package com.remmedy.pharma_box.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class NotificationSettingDto {
    private UUID id;
    private boolean notifyByEmail;
    private boolean notifyByPush;
    private int daysBefore;
}
