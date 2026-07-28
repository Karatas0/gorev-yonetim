package com.detaysoft.gorev_yonetim.dto;

import lombok.Data;

@Data
public class AttachmentResponseDto {
    private Long id;
    private String fileName;
    private String fileType;
    private Long taskId;
    private String taskTitle;
}