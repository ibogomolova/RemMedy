package com.remmedy.pharma_box.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class JwtResponseDto {
    private String token;
    private UUID userId;
    private String email;
}
