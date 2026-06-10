package com.krysha.bookreview.service;

import com.krysha.bookreview.dto.AuthResponse;
import com.krysha.bookreview.dto.LoginRequest;
import com.krysha.bookreview.dto.RegisterRequest;
import com.krysha.bookreview.model.Role;
import com.krysha.bookreview.model.User;
import com.krysha.bookreview.repository.UserRepository;
import com.krysha.bookreview.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setFirstname(request.getFirstname());
        user.setBirthDate(request.getBirthDate());
        user.setRole(Role.USER);

        userRepository.save(user);

        var token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}