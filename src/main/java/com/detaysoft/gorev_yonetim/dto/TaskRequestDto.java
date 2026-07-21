package com.detaysoft.gorev_yonetim.dto;

import com.detaysoft.gorev_yonetim.enums.TaskPriority;
import com.detaysoft.gorev_yonetim.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequestDto {

    @NotBlank(message = "Görev başlığı boş olamaz")
    private String title;

    private String description;

    @NotNull(message = "Durum boş olamaz")
    private TaskStatus status;

    @NotNull(message = "Öncelik boş olamaz")
    private TaskPriority priority;

    @NotNull(message = "Proje ID boş olamaz")
    private Long projectId;

    private Long assignedUserId;
}