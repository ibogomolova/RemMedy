package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.CreateMedicineDto;
import com.remmedy.pharma_box.dto.MedicineDto;
import com.remmedy.pharma_box.entity.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicineMapper {

    @Mapping(source = "user.id", target = "userId")
    MedicineDto toDto(Medicine medicine);

    @Mapping(source = "userId", target = "user.id")
    Medicine toEntity(CreateMedicineDto dto);
}
