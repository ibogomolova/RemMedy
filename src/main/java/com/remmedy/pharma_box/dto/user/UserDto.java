package com.remmedy.pharma_box.dto.user;

import com.remmedy.pharma_box.model.enums.Role;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDto(

        UUID id,

        String firstName,

        String lastName,

        String email,

        Role role
) {
}
