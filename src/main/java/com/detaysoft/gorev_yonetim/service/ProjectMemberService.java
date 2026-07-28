package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.ProjectMemberRequestDto;
import com.detaysoft.gorev_yonetim.dto.ProjectMemberResponseDto;

import java.util.List;

public interface ProjectMemberService {
    ProjectMemberResponseDto addMember(ProjectMemberRequestDto requestDto);
    List<ProjectMemberResponseDto> getMembersByProjectId(Long projectId);
    List<ProjectMemberResponseDto> getProjectsByUserId(Long userId);
    void removeMember(Long projectId, Long userId);
}