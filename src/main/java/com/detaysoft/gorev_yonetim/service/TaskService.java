package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.TaskRequestDto;
import com.detaysoft.gorev_yonetim.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto requestDto);
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto getTaskById(Long id);
    TaskResponseDto updateTask(Long id, TaskRequestDto requestDto);
    void deleteTask(Long id);
}