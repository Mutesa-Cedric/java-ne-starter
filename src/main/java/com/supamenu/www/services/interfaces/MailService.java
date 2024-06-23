package com.supamenu.www.services.interfaces;

public interface MailService {
    void sendEmail(String to, String subject, String content, boolean isHtmlContent);
}
