package com.remmedy.pharma_box.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMailController {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/test-email")
    public String sendTestEmail(@RequestParam String to) {
        try {
            var msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(to);
            msg.setSubject("Проверка SMTP2");
            msg.setText("Это тестовое письмо из приложения RemMedy.");
            mailSender.send(msg);
            return "Письмо принято SMTP.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при отправке: " + e.getMessage();
        }
    }
}
