package com.remmedy.pharma_box.dto.medicine;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record MedicineNew(

        @NotBlank(message = "First name cannot be empty")
        @Size(min = 2, max = 255, message = "First Name length should be in the range from 2 to 255 characters.")
        String name,

        @NotNull(message = "Expiration date is required.")
        @Future(message = "Expiration date must be in the future.")
        LocalDate expirationDate,

        @Pattern(                            // При необходимости добавлю @NotBlank
                regexp = "^[0-9]{8,20}$",
                message = "Barcode must contain only digits and be 8–20 characters long."
        )
        String barcode,

        @Pattern(
                regexp = "^(https?://).+",
                message = "Front image URL must be a valid HTTP/HTTPS link."
        )
        String frontImageUrl,

        @NotNull(message = "User ID is required.")
        UUID userId
) {
}
