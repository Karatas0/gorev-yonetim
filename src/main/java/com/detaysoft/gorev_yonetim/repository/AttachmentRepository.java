package com.detaysoft.gorev_yonetim.repository;

import com.detaysoft.gorev_yonetim.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findByTaskId(Long taskId);
}