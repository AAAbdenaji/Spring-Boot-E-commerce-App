package com.misc.sandboxproj.DTOs.AuthDTOS;

public record RegisterRequest(
        String username,
        String email,
        String password
) {}
