package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.CommentRequestDto;
import com.detaysoft.gorev_yonetim.dto.CommentResponseDto;
import com.detaysoft.gorev_yonetim.entity.Comment;
import com.detaysoft.gorev_yonetim.entity.Task;
import com.detaysoft.gorev_yonetim.entity.User;
import com.detaysoft.gorev_yonetim.exception.ResourceNotFoundException;
import com.detaysoft.gorev_yonetim.repository.CommentRepository;
import com.detaysoft.gorev_yonetim.repository.TaskRepository;
import com.detaysoft.gorev_yonetim.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        Task task = taskRepository.findById(requestDto.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Görev bulunamadı: " + requestDto.getTaskId()));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı: " + requestDto.getUserId()));

        Comment comment = new Comment();
        comment.setContent(requestDto.getContent());
        comment.setTask(task);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);
        return toResponseDto(savedComment);
    }

    @Override
    public List<CommentResponseDto> getCommentsByTaskId(Long taskId) {
        taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Görev bulunamadı: " + taskId));

        return commentRepository.findByTaskId(taskId)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private CommentResponseDto toResponseDto(Comment comment) {
        CommentResponseDto dto = new CommentResponseDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setTaskId(comment.getTask().getId());
        dto.setTaskTitle(comment.getTask().getTitle());
        dto.setUserId(comment.getUser().getId());
        dto.setUserFullName(comment.getUser().getFirstName() + " " + comment.getUser().getLastName());
        return dto;
    }
}