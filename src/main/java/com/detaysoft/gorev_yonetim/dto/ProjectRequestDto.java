package com.detaysoft.gorev_yonetim.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRequestDto {

    @NotBlank(message = "Proje adı boş olamaz")
    private String name;

    private String description;
}