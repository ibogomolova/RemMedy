package com.remmedy.pharma_box.controller;

import com.remmedy.pharma_box.dto.medicine.MedicineDto;
import com.remmedy.pharma_box.dto.medicine.MedicineNew;
import com.remmedy.pharma_box.dto.medicine.MedicineUpdate;
import com.remmedy.pharma_box.service.MedicineService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/medicines")
@Slf4j
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicineDto createMedicine(@Valid @RequestBody MedicineNew medicineNew) {
        log.info("Creating a new medicine for a user with id: {}", medicineNew.userId());
        return medicineService.createMedicine(medicineNew);
    }

    @GetMapping
    public List<MedicineDto> getAllMedicines(@RequestParam(defaultValue = "0")
                                             @PositiveOrZero(message = "Page index must be 0 or greater") int page,
                                             @RequestParam(defaultValue = "10")
                                             @Positive(message = "Page size must be positive") int size) {
        return medicineService.getAllMedicines(page, size);
    }

    @GetMapping("/user/{userId}")
    public List<MedicineDto> getMedicinesByUserId(@PathVariable UUID userId,
                                                  @RequestParam(defaultValue = "0")
                                                  @PositiveOrZero(message = "Page index must be 0 or greater") int page,
                                                  @RequestParam(defaultValue = "10")
                                                  @Positive(message = "Page size must be positive") int size) {
        log.info("Getting all medicines for a user with id: {} with page: {} and size: {}", userId, page, size);
        return medicineService.getMedicinesByUserId(userId, page, size);
    }

    @GetMapping("/{medicineId}")
    public MedicineDto getMedicineById(@PathVariable UUID medicineId) {
        return medicineService.getMedicineById(medicineId);
    }

    @PatchMapping("/{medicineId}")
    public MedicineDto updateMedicineById(@PathVariable UUID medicineId,
                                          @Valid @RequestBody MedicineUpdate medicineUpdate) {
        log.info("Updating a medicine by ID {}.", medicineId);
        return medicineService.updateMedicineById(medicineId, medicineUpdate);
    }

    @DeleteMapping("/{medicineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedicineById(@PathVariable UUID medicineId) {
        log.info("Deleting a medicine by ID {}.", medicineId);
        medicineService.deleteMedicineById(medicineId);
    }
}
