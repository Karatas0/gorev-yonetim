package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.ProjectRequestDto;
import com.detaysoft.gorev_yonetim.dto.ProjectResponseDto;
import com.detaysoft.gorev_yonetim.entity.Project;
import com.detaysoft.gorev_yonetim.exception.ResourceNotFoundException;
import com.detaysoft.gorev_yonetim.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto requestDto) {
        Project project = new Project();
        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());
        Project savedProject = projectRepository.save(project);
        return toResponseDto(savedProject);
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proje bulunamadı: " + id));
        return toResponseDto(project);
    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto requestDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proje bulunamadı: " + id));
        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());
        Project updatedProject = projectRepository.save(project);
        return toResponseDto(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectResponseDto toResponseDto(Project project) {
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        return dto;
    }
}