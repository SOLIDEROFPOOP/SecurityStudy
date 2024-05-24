package com.example.jwtstudy.controller;

import com.example.jwtstudy.dto.JwtAuthenticationResponse;
import com.example.jwtstudy.dto.SignInRequest;
import com.example.jwtstudy.dto.SignUpRequest;
import com.example.jwtstudy.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthService authService;
    @Operation(summary = "регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request){
        return authService.signUp(request);
    }
    @Operation(summary = "авторизация")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request){
        return authService.signIn(request);
    }
}
