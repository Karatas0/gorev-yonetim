package com.detaysoft.gorev_yonetim.repository;

import com.detaysoft.gorev_yonetim.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
