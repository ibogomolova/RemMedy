package com.remmedy.pharma_box.service;

import org.springframework.scheduling.annotation.Scheduled;

public interface ReminderService {

    @Scheduled(cron = "0 0 8 * * ?")
    void processReminders();
}
