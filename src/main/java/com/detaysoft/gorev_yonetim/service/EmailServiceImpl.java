package com.detaysoft.gorev_yonetim.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendTaskAssignmentEmail(String toEmail, String userName, String taskTitle) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@gorev-yonetim.com");
            message.setTo(toEmail);
            message.setSubject("Yeni Görev Atandı");
            message.setText("Merhaba " + userName + ",\n\n" +
                    "Size '" + taskTitle + "' görevi atandı.\n\n" +
                    "İyi çalışmalar,\n" +
                    "Görev Yönetim Sistemi");

            mailSender.send(message);
            log.info("Email gönderildi. Alıcı: {}, Görev: {}", toEmail, taskTitle);
        } catch (Exception e) {
            log.error("Email gönderilemedi. Alıcı: {}, Hata: {}", toEmail, e.getMessage());
        }
    }
}