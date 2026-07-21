package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.ProjectRequestDto;
import com.detaysoft.gorev_yonetim.dto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    ProjectResponseDto createProject(ProjectRequestDto requestDto);
    List<ProjectResponseDto> getAllProjects();
    ProjectResponseDto getProjectById(Long id);
    ProjectResponseDto updateProject(Long id, ProjectRequestDto requestDto);
    void deleteProject(Long id);
}