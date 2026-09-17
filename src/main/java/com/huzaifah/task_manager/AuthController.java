package com.huzaifah.task_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

// @RequestMapping("/auth") sets a base URL for all endpoints in this controller
// so @PostMapping("/login") becomes POST /auth/login
// and @PostMapping("/register") becomes POST /auth/register
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // temporary — just for creating test users with hashed passwords
    // in a real app this would be a proper registration flow
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // login endpoint — takes email and password, returns a JWT token if correct
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }

    // temporary register endpoint — creates a user with a hashed password for testing
    @PostMapping("/register")
    public String register(@RequestBody LoginRequest request) {
        User user = new User();
        user.setName("Test User");
        user.setEmail(request.getEmail());
        // encode() hashes the password before storing — never store plain text passwords
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return "User registered";
    }
}