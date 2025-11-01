package com.remmedy.pharma_box.dto.medicine;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record MedicineDto(

        UUID id,

        String name,

        LocalDate addedAt,

        LocalDate expirationDate,

        String barcode,

        String frontImageUrl,

        UUID userId
) {
}
