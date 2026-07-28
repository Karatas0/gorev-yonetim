package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.ProjectMemberRequestDto;
import com.detaysoft.gorev_yonetim.dto.ProjectMemberResponseDto;
import com.detaysoft.gorev_yonetim.entity.Project;
import com.detaysoft.gorev_yonetim.entity.ProjectMember;
import com.detaysoft.gorev_yonetim.entity.User;
import com.detaysoft.gorev_yonetim.exception.ResourceNotFoundException;
import com.detaysoft.gorev_yonetim.repository.ProjectMemberRepository;
import com.detaysoft.gorev_yonetim.repository.ProjectRepository;
import com.detaysoft.gorev_yonetim.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public ProjectMemberResponseDto addMember(ProjectMemberRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Proje bulunamadı: " + requestDto.getProjectId()));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı: " + requestDto.getUserId()));

        if (projectMemberRepository.existsByProjectIdAndUserId(requestDto.getProjectId(), requestDto.getUserId())) {
            throw new RuntimeException("Kullanıcı zaten bu projenin üyesi");
        }

        ProjectMember member = new ProjectMember();
        member.setProject(project);
        member.setUser(user);

        ProjectMember savedMember = projectMemberRepository.save(member);
        log.info("Kullanıcı projeye eklendi. Kullanıcı ID: {}, Proje ID: {}", user.getId(), project.getId());
        return toResponseDto(savedMember);
    }

    @Override
    public List<ProjectMemberResponseDto> getMembersByProjectId(Long projectId) {
        projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Proje bulunamadı: " + projectId));
        return projectMemberRepository.findByProjectId(projectId)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectMemberResponseDto> getProjectsByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı: " + userId));
        return projectMemberRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeMember(Long projectId, Long userId) {
        ProjectMember member = projectMemberRepository.findByProjectIdAndUserId(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Üyelik bulunamadı"));
        projectMemberRepository.delete(member);
        log.info("Kullanıcı projeden çıkarıldı. Kullanıcı ID: {}, Proje ID: {}", userId, projectId);
    }

    private ProjectMemberResponseDto toResponseDto(ProjectMember member) {
        ProjectMemberResponseDto dto = new ProjectMemberResponseDto();
        dto.setId(member.getId());
        dto.setProjectId(member.getProject().getId());
        dto.setProjectName(member.getProject().getName());
        dto.setUserId(member.getUser().getId());
        dto.setUserFullName(member.getUser().getFirstName() + " " + member.getUser().getLastName());
        if (member.getUser().getRole() != null) {
            dto.setUserRole(member.getUser().getRole().getName().name());
        }
        return dto;
    }
}