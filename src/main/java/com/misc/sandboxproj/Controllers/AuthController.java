package com.misc.sandboxproj.Controllers;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misc.sandboxproj.DTOs.AuthDTOS.LoginRequest;
import com.misc.sandboxproj.DTOs.AuthDTOS.RegisterRequest;
import com.misc.sandboxproj.Helpers.RoleName;
import com.misc.sandboxproj.Repositories.RoleRepository;
import com.misc.sandboxproj.Repositories.UserRepository;
import com.misc.sandboxproj.Service.JWTService;
import com.misc.sandboxproj.models.Role;
import com.misc.sandboxproj.models.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        Authentication auth =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.username(),
                                request.password()
                        )
                );

        UserDetails user =
                (UserDetails) auth.getPrincipal();

        return jwtService.generateToken(user);
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

    Role role = roleRepository
            .findByName(RoleName.ROLE_USER)
            .orElseThrow();

    User user = User.builder()
            .username(request.username())
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .roles(Set.of(role))
            .enabled(true)
            .build();

    userRepository.save(user);

    return "registered.";
}
}
