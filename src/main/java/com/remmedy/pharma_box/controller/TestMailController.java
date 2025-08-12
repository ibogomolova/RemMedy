package com.remmedy.pharma_box.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/test-email")
    public String sendTestEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@remmedy.site");
            message.setTo("ibogomolllova@gmail.com");
            message.setSubject("Проверка SMTP");
            message.setText("Это тестовое письмо из приложения RemMedy.");

            mailSender.send(message);

            return "Письмо отправлено!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при отправке: " + e.getMessage();
        }
    }
}
