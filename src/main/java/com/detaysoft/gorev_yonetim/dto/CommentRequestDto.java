package com.detaysoft.gorev_yonetim.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequestDto {

    @NotBlank(message = "Yorum içeriği boş olamaz")
    private String content;

    @NotNull(message = "Görev ID boş olamaz")
    private Long taskId;

    @NotNull(message = "Kullanıcı ID boş olamaz")
    private Long userId;
}