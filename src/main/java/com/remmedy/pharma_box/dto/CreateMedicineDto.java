package com.remmedy.pharma_box.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateMedicineDto {
    @NotBlank
    private String name;
    @NotNull
    @Future
    private LocalDate expirationDate;
    private String barcode;
    private String frontImageUrl;
    @NotNull
    private UUID userId;
}
