package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.CreateMedicineDto;
import com.remmedy.pharma_box.dto.MedicineDto;
import com.remmedy.pharma_box.entity.Medicine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicineMapper {
    MedicineDto toDto(Medicine medicine);
    Medicine toEntity(CreateMedicineDto dto);
}
