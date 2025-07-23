package com.remmedy.pharma_box.service;

import com.remmedy.pharma_box.dto.CreateMedicineDto;
import com.remmedy.pharma_box.dto.MedicineDto;

import java.util.List;
import java.util.UUID;

public interface MedicineService {
    MedicineDto create(CreateMedicineDto dto);
    List<MedicineDto> getAllByUser(UUID userId);
    MedicineDto getById(UUID id);
    MedicineDto update(UUID id, MedicineDto dto);
    void delete(UUID id);
}
