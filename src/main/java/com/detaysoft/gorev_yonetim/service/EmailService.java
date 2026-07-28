package com.detaysoft.gorev_yonetim.service;

public interface EmailService {
    void sendTaskAssignmentEmail(String toEmail, String userName, String taskTitle);
}