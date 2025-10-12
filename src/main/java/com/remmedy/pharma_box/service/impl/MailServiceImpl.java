package com.remmedy.pharma_box.service.impl;

import com.remmedy.pharma_box.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;


    @Override
    public void sendEmail(String to, String subject, String text) {
        try {
            var mm = mailSender.createMimeMessage();
            var helper = new org.springframework.mail.javamail.MimeMessageHelper(mm, "UTF-8");
            helper.setFrom(from, "RemMedy");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(
                    "<p>Это уведомление из <b>RemMedy</b>.</p><p>" + text + "</p>",
                    true
            );
            mm.addHeader("List-Unsubscribe", "<mailto:" + from + ">");
            mailSender.send(mm);
        } catch (Exception e) {
            throw new RuntimeException("Mail send failed", e);
        }
    }
}
