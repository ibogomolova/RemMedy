package com.remmedy.pharma_box.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserUpdate(

        @Size(min = 2, max = 255, message = "First name must be between 2 and 255 characters")
        String firstName,

        @Size(min = 2, max = 255, message = "Last name must be between 2 and 255 characters")
        String lastName,

        @Email(regexp = "^(?=.{1,64}@)[a-z0-9._-]+(\\.[a-z0-9._-]+)*@[^-][a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,6})$",
                message = "Invalid email format")
        String email
) {
}
