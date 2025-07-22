package com.remmedy.pharma_box.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReminderDto {
    private UUID id;
    private LocalDateTime notifyAt;
    private boolean sent;
}
