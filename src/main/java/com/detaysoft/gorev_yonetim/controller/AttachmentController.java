package com.detaysoft.gorev_yonetim.controller;

import com.detaysoft.gorev_yonetim.dto.AttachmentResponseDto;
import com.detaysoft.gorev_yonetim.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping(value = "/upload/{taskId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AttachmentResponseDto> uploadFile(
            @PathVariable Long taskId,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(attachmentService.uploadFile(taskId, file));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<AttachmentResponseDto>> getAttachmentsByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(attachmentService.getAttachmentsByTaskId(taskId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Long id) {
        attachmentService.deleteAttachment(id);
        return ResponseEntity.noContent().build();
    }
}