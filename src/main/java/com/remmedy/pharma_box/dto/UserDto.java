package com.remmedy.pharma_box.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String name;
    private String email;
}
