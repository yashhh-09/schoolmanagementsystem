package com.school.controller;

import com.school.dto.*;
import com.school.entity.User;
import com.school.repository.UserRepository;
import com.school.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthController(UserRepository users, PasswordEncoder encoder, JwtService jwt) {
        this.users = users; this.encoder = encoder; this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody LoginRequest request) {
        if (users.findByUsername(request.username()).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        User u = new User(null, request.username(), encoder.encode(request.password()), "USER");
        users.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        User u = users.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        if (!encoder.matches(request.password(), u.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        return ResponseEntity.ok(new LoginResponse(jwt.generate(u.getUsername(), u.getRole()), u.getUsername(), u.getRole()));
    }
}
