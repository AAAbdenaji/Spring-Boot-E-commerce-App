package com.misc.sandboxproj.DTOs.AuthDTOS;

public record LoginRequest(
        String username,
        String password
) {}