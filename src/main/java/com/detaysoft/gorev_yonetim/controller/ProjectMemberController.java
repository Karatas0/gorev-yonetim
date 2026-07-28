package com.detaysoft.gorev_yonetim.controller;

import com.detaysoft.gorev_yonetim.dto.ProjectMemberRequestDto;
import com.detaysoft.gorev_yonetim.dto.ProjectMemberResponseDto;
import com.detaysoft.gorev_yonetim.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-members")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @PostMapping
    public ResponseEntity<ProjectMemberResponseDto> addMember(@Valid @RequestBody ProjectMemberRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.addMember(requestDto));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectMemberResponseDto>> getMembersByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectMemberService.getMembersByProjectId(projectId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectMemberResponseDto>> getProjectsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(projectMemberService.getProjectsByUserId(userId));
    }

    @DeleteMapping("/project/{projectId}/user/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long projectId, @PathVariable Long userId) {
        projectMemberService.removeMember(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}