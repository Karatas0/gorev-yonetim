package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.TaskRequestDto;
import com.detaysoft.gorev_yonetim.dto.TaskResponseDto;
import com.detaysoft.gorev_yonetim.entity.Project;
import com.detaysoft.gorev_yonetim.entity.Task;
import com.detaysoft.gorev_yonetim.entity.User;
import com.detaysoft.gorev_yonetim.exception.ResourceNotFoundException;
import com.detaysoft.gorev_yonetim.repository.ProjectRepository;
import com.detaysoft.gorev_yonetim.repository.TaskRepository;
import com.detaysoft.gorev_yonetim.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.detaysoft.gorev_yonetim.enums.TaskPriority;
import com.detaysoft.gorev_yonetim.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponseDto createTask(TaskRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Proje bulunamadı: " + requestDto.getProjectId()));

        Task task = new Task();
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setStatus(requestDto.getStatus());
        task.setPriority(requestDto.getPriority());
        task.setProject(project);

        if (requestDto.getAssignedUserId() != null) {
            User user = userRepository.findById(requestDto.getAssignedUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı: " + requestDto.getAssignedUserId()));
            task.setAssignedUser(user);
        }

        Task savedTask = taskRepository.save(task);
        return toResponseDto(savedTask);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TaskResponseDto> getAllTasksPageable(Pageable pageable) {
        return taskRepository.findAll(pageable)
                .map(this::toResponseDto);
    }

    @Override
    public Page<TaskResponseDto> getTasksByStatus(TaskStatus status, Pageable pageable) {
        return taskRepository.findByStatus(status, pageable)
                .map(this::toResponseDto);
    }

    @Override
    public Page<TaskResponseDto> getTasksByPriority(TaskPriority priority, Pageable pageable) {
        return taskRepository.findByPriority(priority, pageable)
                .map(this::toResponseDto);
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Görev bulunamadı: " + id));
        return toResponseDto(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto requestDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Görev bulunamadı: " + id));

        Project project = projectRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Proje bulunamadı: " + requestDto.getProjectId()));

        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setStatus(requestDto.getStatus());
        task.setPriority(requestDto.getPriority());
        task.setProject(project);

        if (requestDto.getAssignedUserId() != null) {
            User user = userRepository.findById(requestDto.getAssignedUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı: " + requestDto.getAssignedUserId()));
            task.setAssignedUser(user);
        } else {
            task.setAssignedUser(null);
        }

        Task updatedTask = taskRepository.save(task);
        return toResponseDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskResponseDto toResponseDto(Task task) {
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setProjectId(task.getProject().getId());
        dto.setProjectName(task.getProject().getName());

        if (task.getAssignedUser() != null) {
            dto.setAssignedUserId(task.getAssignedUser().getId());
            dto.setAssignedUserFullName(task.getAssignedUser().getFirstName() + " " + task.getAssignedUser().getLastName());
        }

        return dto;
    }
}