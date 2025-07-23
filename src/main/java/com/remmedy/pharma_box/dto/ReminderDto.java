package com.remmedy.pharma_box.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReminderDto {
    private UUID id;
    private LocalDateTime notifyAt;
    private boolean sent;
    private UUID medicineId;
}
