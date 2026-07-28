package com.detaysoft.gorev_yonetim.service;

import com.detaysoft.gorev_yonetim.dto.UserRequestDto;
import com.detaysoft.gorev_yonetim.dto.UserResponseDto;
import com.detaysoft.gorev_yonetim.entity.User;
import com.detaysoft.gorev_yonetim.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.detaysoft.gorev_yonetim.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        log.info("Yeni kullanıcı oluşturuluyor: {}", requestDto.getEmail());
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User savedUser = userRepository.save(user);
        log.info("Kullanıcı başarıyla oluşturuldu. ID: {}", savedUser.getId());
        return toResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        log.info("Kullanıcı aranıyor. ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Kullanıcı bulunamadı. ID: {}", id);
                    return new ResourceNotFoundException("Kullanıcı bulunamadı: " + id);
                });
        return toResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        log.info("Kullanıcı güncelleniyor. ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Kullanıcı bulunamadı. ID: {}", id);
                    return new ResourceNotFoundException("Kullanıcı bulunamadı: " + id);
                });
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User updatedUser = userRepository.save(user);
        log.info("Kullanıcı başarıyla güncellendi. ID: {}", updatedUser.getId());
        return toResponseDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Kullanıcı siliniyor. ID: {}", id);
        userRepository.deleteById(id);
        log.info("Kullanıcı başarıyla silindi. ID: {}", id);
    }

    private UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}