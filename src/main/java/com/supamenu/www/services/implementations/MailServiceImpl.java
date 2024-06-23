package com.supamenu.www.services.implementations;

import com.supamenu.www.exceptions.BadRequestException;
import com.supamenu.www.exceptions.InternalServerErrorException;
import com.supamenu.www.services.interfaces.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    @Value("${app_name}")
    private String appName;

    @Override
    @Async
    public void sendEmail(String to, String subject, String content, boolean isHtmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(to);
            helper.setFrom(appName);
            helper.setSubject(subject);
            helper.setText(content, isHtmlContent);
            mailSender.send(message);
        } catch (MessagingException exception) {
            throw new BadRequestException("Failed To Send Email: " + exception.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorException("An error occurred while sending email");
        }
    }
}
