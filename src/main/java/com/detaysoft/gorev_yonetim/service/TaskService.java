package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.TaskRequestDto;
import com.detaysoft.gorev_yonetim.dto.TaskResponseDto;
import com.detaysoft.gorev_yonetim.enums.TaskPriority;
import com.detaysoft.gorev_yonetim.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto requestDto);
    List<TaskResponseDto> getAllTasks();
    Page<TaskResponseDto> getAllTasksPageable(Pageable pageable);
    Page<TaskResponseDto> getTasksByStatus(TaskStatus status, Pageable pageable);
    Page<TaskResponseDto> getTasksByPriority(TaskPriority priority, Pageable pageable);
    TaskResponseDto getTaskById(Long id);
    TaskResponseDto updateTask(Long id, TaskRequestDto requestDto);
    void deleteTask(Long id);
}