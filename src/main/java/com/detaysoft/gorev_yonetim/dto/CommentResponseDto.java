package com.detaysoft.gorev_yonetim.dto;

import lombok.Data;

@Data
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long taskId;
    private String taskTitle;
    private Long userId;
    private String userFullName;
}