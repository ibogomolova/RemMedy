package com.remmedy.pharma_box.service;

import com.remmedy.pharma_box.dto.medicine.MedicineDto;
import com.remmedy.pharma_box.dto.medicine.MedicineNew;
import com.remmedy.pharma_box.dto.medicine.MedicineUpdate;

import java.util.List;
import java.util.UUID;

public interface MedicineService {

    MedicineDto createMedicine(MedicineNew medicineNew);

    List<MedicineDto> getAllMedicines(int page, int size);

    List<MedicineDto> getMedicinesByUserId(UUID userId, int page, int size);

    MedicineDto getMedicineById(UUID medicineId);

    MedicineDto updateMedicineById(UUID medicineId, MedicineUpdate medicineUpdate);

    void deleteMedicineById(UUID medicineId);
}