package com.remmedy.pharma_box.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserNew(

        @NotBlank(message = "First name cannot be empty")
        @Size(min = 2, max = 255, message = "First Name length should be in the range from 2 to 255 characters.")
        String firstname,

        @NotBlank(message = "Last name cannot be empty")
        @Size(min = 2, max = 255, message = "Last name length should be in the range from 2 to 255 characters.")
        String lastName,

        @NotBlank(message = "Email cannot be empty")
        @Email(regexp = "^(?=.{1,64}@)[a-z0-9_-]+(\\.[a-z0-9_-]+)*@[^-][a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,6})$",
                message = "Invalid email format")
        String email,

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 4, max = 20, message = "Password length should be in the range from 4 to 20 characters.")
        String password
) {
}

