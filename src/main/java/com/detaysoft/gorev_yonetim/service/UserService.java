package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.UserRequestDto;
import com.detaysoft.gorev_yonetim.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(Long id, UserRequestDto requestDto);
    void deleteUser(Long id);
}