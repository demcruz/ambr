package br.com.system.auth.ambr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.system.auth.ambr.dto.*;
import br.com.system.auth.ambr.model.User;
import br.com.system.auth.ambr.repository.UserRepository;
import br.com.system.auth.ambr.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        logger.info("🔔 Register request for username={}", request.getUsername());

        var user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role("USER")
                .build();

        userRepository.save(user);
        logger.info("✅ User saved with id={}", user.getId());
        return "Usuário registrado com sucesso!";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        logger.info("🔔 Login request for username={}", request.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
        String token = jwtUtil.generateToken(request.getUsername());
        logger.info("✅ Token generated for username={}", request.getUsername());

        return new AuthResponse(token);
    }


}
