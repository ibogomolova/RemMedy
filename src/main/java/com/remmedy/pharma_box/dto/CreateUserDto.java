package com.remmedy.pharma_box.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;
}

