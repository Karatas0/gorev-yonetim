package com.detaysoft.gorev_yonetim.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectMemberRequestDto {

    @NotNull(message = "Proje ID boş olamaz")
    private Long projectId;

    @NotNull(message = "Kullanıcı ID boş olamaz")
    private Long userId;
}