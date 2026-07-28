package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.AttachmentResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {
    AttachmentResponseDto uploadFile(Long taskId, MultipartFile file);
    List<AttachmentResponseDto> getAttachmentsByTaskId(Long taskId);
    void deleteAttachment(Long id);
}