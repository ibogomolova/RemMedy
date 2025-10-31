package com.remmedy.pharma_box.dto.medicine;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MedicineUpdate(

        @Size(min = 2, max = 255, message = "Name length must be between 2 and 255 characters.")
        String name,

        @Future(message = "Expiration date must be in the future.")
        LocalDate expirationDate,

        @Pattern(
                regexp = "^[0-9]{8,20}$",
                message = "Barcode must contain only digits and be 8–20 characters long."
        )
        String barcode,

        @Pattern(
                regexp = "^(https?://).+",
                message = "Front image URL must be a valid HTTP/HTTPS link."
        )
        String frontImageUrl
) {
}
