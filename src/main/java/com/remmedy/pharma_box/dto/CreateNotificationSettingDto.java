package com.remmedy.pharma_box.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateNotificationSettingDto {
    private boolean notifyByEmail;
    private boolean notifyByPush;
    @Min(1)
    private int daysBefore;
    @NotNull
    private UUID userId;
}
