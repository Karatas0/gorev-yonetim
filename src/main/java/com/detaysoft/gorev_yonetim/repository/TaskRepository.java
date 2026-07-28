package com.detaysoft.gorev_yonetim.repository;

import com.detaysoft.gorev_yonetim.entity.Task;
import com.detaysoft.gorev_yonetim.enums.TaskStatus;
import com.detaysoft.gorev_yonetim.enums.TaskPriority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatus(TaskStatus status, Pageable pageable);
    Page<Task> findByPriority(TaskPriority priority, Pageable pageable);
    List<Task> findByAssignedUserId(Long userId);
}