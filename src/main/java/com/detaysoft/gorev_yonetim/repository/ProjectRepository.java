package com.detaysoft.gorev_yonetim.repository;

import com.detaysoft.gorev_yonetim.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}