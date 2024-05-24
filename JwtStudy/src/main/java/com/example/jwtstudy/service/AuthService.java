package com.example.jwtstudy.service;

import com.example.jwtstudy.dto.JwtAuthenticationResponse;
import com.example.jwtstudy.dto.SignInRequest;
import com.example.jwtstudy.dto.SignUpRequest;
import com.example.jwtstudy.entity.Role;
import com.example.jwtstudy.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    @Autowired
    public JwtService jwtService;
    public final PasswordEncoder passwordEncoder;
    public final AuthenticationManager authenticationManager;


    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
    public JwtAuthenticationResponse signIn(SignInRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userService.userDetailsService().loadUserByUsername(request.getUsername());
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
