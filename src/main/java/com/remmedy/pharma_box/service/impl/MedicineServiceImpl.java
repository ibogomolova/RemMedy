package com.remmedy.pharma_box.service.impl;

import com.remmedy.pharma_box.dto.medicine.MedicineDto;
import com.remmedy.pharma_box.dto.medicine.MedicineNew;
import com.remmedy.pharma_box.dto.medicine.MedicineUpdate;
import com.remmedy.pharma_box.exception.MedicineNotFoundException;
import com.remmedy.pharma_box.exception.UserNotFoundException;
import com.remmedy.pharma_box.mapper.MedicineMapper;
import com.remmedy.pharma_box.model.Medicine;
import com.remmedy.pharma_box.model.User;
import com.remmedy.pharma_box.repository.MedicineRepository;
import com.remmedy.pharma_box.repository.UserRepository;
import com.remmedy.pharma_box.service.MedicineService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final MedicineMapper medicineMapper;


    @Override
    @Transactional
    public MedicineDto createMedicine(MedicineNew medicineNew) {
        User user = userRepository.findById(medicineNew.userId())
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with ID = %s not found", medicineNew.userId())));

        Medicine medicine = medicineMapper.toMedicine(medicineNew);
        medicine.setUser(user);

        Medicine savedMedicine = medicineRepository.save(medicine);
        MedicineDto dto = medicineMapper.toMedicineDto(savedMedicine);

        log.info("Medicine with ID = {} created.", dto.id());
        return dto;
    }

    @Override
    public List<MedicineDto> getAllMedicines(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Medicine> medicinePage = medicineRepository.findAll(pageRequest);

        List<MedicineDto> dtoList = medicineMapper.toMedicineDtoList(medicinePage.getContent());
        log.info("Medicine list on page {} with size {} returned.", page, size);
        return dtoList;
    }

    @Override
    public List<MedicineDto> getMedicinesByUserId(UUID userId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<Medicine> medicinePage = medicineRepository.findByUserId(userId, pageRequest);
        List<MedicineDto> dtoList = medicineMapper.toMedicineDtoList(medicinePage.getContent());

        if (dtoList.isEmpty()) {
            log.warn("No medicines found for user ID = {}", userId);
        }
        return dtoList;
    }

    @Override
    public MedicineDto getMedicineById(UUID medicineId) {
        Medicine medicine = findMedicineById(medicineId);
        MedicineDto dto = medicineMapper.toMedicineDto(medicine);
        log.info("Medicine with ID = {} returned.", medicineId);
        return dto;
    }

    @Override
    @Transactional
    public MedicineDto updateMedicineById(UUID medicineId, MedicineUpdate medicineUpdate) {
        Medicine medicine = findMedicineByIdForUpdate(medicineId);

        medicineMapper.updateMedicine(medicine, medicineUpdate);
        Medicine updated = medicineRepository.save(medicine);

        MedicineDto dto = medicineMapper.toMedicineDto(updated);
        log.info("Medicine with ID = {} updated.", medicineId);
        return dto;
    }

    @Override
    @Transactional
    public void deleteMedicineById(UUID medicineId) {
        Medicine medicine = findMedicineById(medicineId);
        medicineRepository.delete(medicine);
        log.info("Medicine with ID = {} deleted.", medicineId);
    }

    private Medicine findMedicineById(UUID medicineId) {
        return medicineRepository.findById(medicineId)
                .orElseThrow(() -> new MedicineNotFoundException(
                        String.format("Medicine with ID = %s not found", medicineId)));
    }

    private Medicine findMedicineByIdForUpdate(UUID medicineId) {
        return medicineRepository.findMedicineByIdForUpdate(medicineId)
                .orElseThrow(() -> new MedicineNotFoundException(
                        String.format("Medicine with ID = %s not found", medicineId)));
    }
}
