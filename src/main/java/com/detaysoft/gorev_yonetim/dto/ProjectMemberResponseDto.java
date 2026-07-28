package com.detaysoft.gorev_yonetim.dto;

import lombok.Data;

@Data
public class ProjectMemberResponseDto {
    private Long id;
    private Long projectId;
    private String projectName;
    private Long userId;
    private String userFullName;
    private String userRole;
}