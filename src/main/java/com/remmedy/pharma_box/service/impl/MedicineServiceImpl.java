package com.remmedy.pharma_box.service.impl;

import com.remmedy.pharma_box.dto.CreateMedicineDto;
import com.remmedy.pharma_box.dto.MedicineDto;
import com.remmedy.pharma_box.entity.Medicine;
import com.remmedy.pharma_box.entity.User;
import com.remmedy.pharma_box.exception.MedicineNotFoundException;
import com.remmedy.pharma_box.exception.UserNotFoundException;
import com.remmedy.pharma_box.mapper.MedicineMapper;
import com.remmedy.pharma_box.repository.MedicineRepository;
import com.remmedy.pharma_box.repository.UserRepository;
import com.remmedy.pharma_box.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final MedicineMapper medicineMapper;

    @Override
    public MedicineDto create(CreateMedicineDto dto) {
        Medicine medicine = medicineMapper.toEntity(dto);
        medicine.setAddedAt(LocalDate.now());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        medicine.setUser(user);

        Medicine saved = medicineRepository.save(medicine);
        return medicineMapper.toDto(saved);
    }

    @Override
    public List<MedicineDto> getAllByUser(UUID userId) {
        return medicineRepository.findByUserId(userId).stream()
                .map(medicineMapper::toDto)
                .toList();
    }

    @Override
    public MedicineDto getById(UUID id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new MedicineNotFoundException("Medicine with id " + id + " not found"));
        return medicineMapper.toDto(medicine);
    }

    @Override
    public MedicineDto update(UUID id, MedicineDto dto) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new MedicineNotFoundException("Medicine with id " + id + " not found"));

        medicine.setName(dto.getName());
        medicine.setExpirationDate(dto.getExpirationDate());
        medicine.setBarcode(dto.getBarcode());
        medicine.setFrontImageUrl(dto.getFrontImageUrl());

        Medicine updated = medicineRepository.save(medicine);
        return medicineMapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!medicineRepository.existsById(id)) {
            throw new MedicineNotFoundException("Medicine with id " + id + " not found");
        }
        medicineRepository.deleteById(id);
    }
}
