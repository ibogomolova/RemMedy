package com.remmedy.pharma_box.controller;

import com.remmedy.pharma_box.dto.ReminderDto;
import com.remmedy.pharma_box.mapper.ReminderMapper;
import com.remmedy.pharma_box.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderRepository reminderRepo;
    private final ReminderMapper reminderMapper;

    // Для админки или тестов
    @GetMapping
    public List<ReminderDto> getAll() {
        return reminderRepo.findAll().stream()
                .map(reminderMapper::toDto)
                .toList();
    }
}
