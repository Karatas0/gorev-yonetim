package com.detaysoft.gorev_yonetim.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}