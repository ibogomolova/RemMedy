package com.remmedy.pharma_box.service;

public interface MailService {
    void sendEmail(String to, String subject, String text);
}
