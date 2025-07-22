package com.remmedy.pharma_box.dto;

import java.time.LocalDate;
import java.util.UUID;

public class MedicineDto {
    private UUID id;
    private String name;
    private LocalDate addedAt;
    private LocalDate expirationDate;
    private String barcode;
    private String frontImageUrl;
}
