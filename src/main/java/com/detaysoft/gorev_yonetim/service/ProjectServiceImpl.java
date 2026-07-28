package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.ProjectRequestDto;
import com.detaysoft.gorev_yonetim.dto.ProjectResponseDto;
import com.detaysoft.gorev_yonetim.entity.Project;
import com.detaysoft.gorev_yonetim.exception.ResourceNotFoundException;
import com.detaysoft.gorev_yonetim.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto requestDto) {
        log.info("Yeni proje oluşturuluyor: {}", requestDto.getName());
        Project project = new Project();
        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());
        Project savedProject = projectRepository.save(project);
        log.info("Proje başarıyla oluşturuldu. ID: {}", savedProject.getId());
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
        log.info("Proje aranıyor. ID: {}", id);
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Proje bulunamadı. ID: {}", id);
                    return new ResourceNotFoundException("Proje bulunamadı: " + id);
                });
        return toResponseDto(project);
    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto requestDto) {
        log.info("Proje güncelleniyor. ID: {}", id);
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Proje bulunamadı. ID: {}", id);
                    return new ResourceNotFoundException("Proje bulunamadı: " + id);
                });
        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());
        Project updatedProject = projectRepository.save(project);
        log.info("Proje başarıyla güncellendi. ID: {}", updatedProject.getId());
        return toResponseDto(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        log.info("Proje siliniyor. ID: {}", id);
        projectRepository.deleteById(id);
        log.info("Proje başarıyla silindi. ID: {}", id);
    }

    private ProjectResponseDto toResponseDto(Project project) {
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        return dto;
    }
}