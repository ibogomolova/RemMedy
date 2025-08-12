package com.remmedy.pharma_box.service.impl;

import com.remmedy.pharma_box.entity.Medicine;
import com.remmedy.pharma_box.entity.NotificationSetting;
import com.remmedy.pharma_box.entity.Reminder;
import com.remmedy.pharma_box.entity.User;
import com.remmedy.pharma_box.repository.MedicineRepository;
import com.remmedy.pharma_box.repository.NotificationSettingRepository;
import com.remmedy.pharma_box.repository.ReminderRepository;
import com.remmedy.pharma_box.repository.UserRepository;
import com.remmedy.pharma_box.service.MailService;
import com.remmedy.pharma_box.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepo;
    private final MedicineRepository medicineRepo;
    private final NotificationSettingRepository settingsRepo;
    private final MailService mailService;


    @Scheduled(cron = "0 0 8 * * ?")
    @Override
    public void processReminders() {
        List<Medicine> medicines = medicineRepo.findAll();
        LocalDate today = LocalDate.now();

        for (Medicine med : medicines) {
            User user = med.getUser();
            NotificationSetting settings = settingsRepo.findByUser(user).orElse(null);
            if (settings == null || settings.getDaysBefore() < 0) continue;

            LocalDate expiration = med.getExpirationDate();
            LocalDate notifyDate = expiration.minusDays(settings.getDaysBefore());

            boolean alreadySent = reminderRepo.existsByMedicineAndSentTrue(med);
            if (!alreadySent && today.isEqual(notifyDate)) {
                if (settings.isNotifyByEmail()) {
                    mailService.sendEmail(user.getEmail(),
                            "Скоро истечёт срок действия лекарства",
                            String.format("Лекарство %s истекает %s", med.getName(), expiration));
                }
                // Если что тут могу сделать notifyByPush

                Reminder reminder = new Reminder();
                reminder.setMedicine(med);
                reminder.setUser(user);
                reminder.setNotifyAt(LocalDateTime.now());
                reminder.setSent(true);
                reminderRepo.save(reminder);
            }
        }
    }
}
