package com.detaysoft.gorev_yonetim.controller;

import com.detaysoft.gorev_yonetim.dto.LoginRequestDto;
import com.detaysoft.gorev_yonetim.dto.LoginResponseDto;
import com.detaysoft.gorev_yonetim.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword())
        );

        String token = jwtUtil.generateToken(requestDto.getEmail());
        return ResponseEntity.ok(new LoginResponseDto(token, requestDto.getEmail()));
    }
}