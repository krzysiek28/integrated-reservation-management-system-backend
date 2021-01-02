package com.uliasz.irms.internal.emailService;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}
