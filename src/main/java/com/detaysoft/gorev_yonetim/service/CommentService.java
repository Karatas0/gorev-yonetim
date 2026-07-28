package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.CommentRequestDto;
import com.detaysoft.gorev_yonetim.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(CommentRequestDto requestDto);
    List<CommentResponseDto> getCommentsByTaskId(Long taskId);
    void deleteComment(Long id);
}