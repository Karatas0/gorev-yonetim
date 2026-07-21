package com.detaysoft.gorev_yonetim.dto;

import com.detaysoft.gorev_yonetim.enums.TaskPriority;
import com.detaysoft.gorev_yonetim.enums.TaskStatus;
import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Long projectId;
    private String projectName;
    private Long assignedUserId;
    private String assignedUserFullName;
}