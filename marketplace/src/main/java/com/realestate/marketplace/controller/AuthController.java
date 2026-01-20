package com.realestate.marketplace.controller;

import com.realestate.marketplace.entity.User;
import com.realestate.marketplace.repository.UserRepository;
import com.realestate.marketplace.service.AuthService;
import com.realestate.marketplace.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    // ✅ SINGLE constructor
    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    // SIGNUP
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return authService.signup(user);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return ResponseEntity.ok(user);
    }
}
