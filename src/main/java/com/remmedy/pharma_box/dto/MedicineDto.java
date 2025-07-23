package com.remmedy.pharma_box.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class MedicineDto {
    private UUID id;
    private String name;
    private LocalDate addedAt;
    private LocalDate expirationDate;
    private String barcode;
    private String frontImageUrl;
}
