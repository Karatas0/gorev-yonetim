package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.AttachmentResponseDto;
import com.detaysoft.gorev_yonetim.entity.Attachment;
import com.detaysoft.gorev_yonetim.entity.Task;
import com.detaysoft.gorev_yonetim.exception.ResourceNotFoundException;
import com.detaysoft.gorev_yonetim.repository.AttachmentRepository;
import com.detaysoft.gorev_yonetim.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final TaskRepository taskRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public AttachmentResponseDto uploadFile(Long taskId, MultipartFile file) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Görev bulunamadı: " + taskId));

        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Attachment attachment = new Attachment();
            attachment.setFileName(file.getOriginalFilename());
            attachment.setFilePath(filePath.toString());
            attachment.setFileType(file.getContentType());
            attachment.setTask(task);

            Attachment savedAttachment = attachmentRepository.save(attachment);
            log.info("Dosya yüklendi. Görev ID: {}, Dosya: {}", taskId, file.getOriginalFilename());
            return toResponseDto(savedAttachment);

        } catch (IOException e) {
            log.error("Dosya yüklenirken hata oluştu: {}", e.getMessage());
            throw new RuntimeException("Dosya yüklenirken hata oluştu: " + e.getMessage());
        }
    }

    @Override
    public List<AttachmentResponseDto> getAttachmentsByTaskId(Long taskId) {
        taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Görev bulunamadı: " + taskId));
        return attachmentRepository.findByTaskId(taskId)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAttachment(Long id) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dosya bulunamadı: " + id));
        try {
            Files.deleteIfExists(Paths.get(attachment.getFilePath()));
        } catch (IOException e) {
            log.error("Dosya silinirken hata oluştu: {}", e.getMessage());
        }
        attachmentRepository.deleteById(id);
        log.info("Dosya silindi. ID: {}", id);
    }

    private AttachmentResponseDto toResponseDto(Attachment attachment) {
        AttachmentResponseDto dto = new AttachmentResponseDto();
        dto.setId(attachment.getId());
        dto.setFileName(attachment.getFileName());
        dto.setFileType(attachment.getFileType());
        dto.setTaskId(attachment.getTask().getId());
        dto.setTaskTitle(attachment.getTask().getTitle());
        return dto;
    }
}