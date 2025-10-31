package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.medicine.MedicineDto;
import com.remmedy.pharma_box.dto.medicine.MedicineNew;
import com.remmedy.pharma_box.dto.medicine.MedicineUpdate;
import com.remmedy.pharma_box.model.Medicine;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicineMapper {

    @Mapping(source = "user.id", target = "userId")
    MedicineDto toMedicineDto(Medicine medicine);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addedAt", expression = "java(LocalDate.now())")
    Medicine toMedicine(MedicineNew medicineNew);

    List<MedicineDto> toMedicineDtoList(List<Medicine> medicineList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMedicine(@MappingTarget Medicine medicine, MedicineUpdate medicineUpdate);
}
