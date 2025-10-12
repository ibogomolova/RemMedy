package com.remmedy.pharma_box.controller;

import com.remmedy.pharma_box.dto.CreateMedicineDto;
import com.remmedy.pharma_box.dto.MedicineDto;
import com.remmedy.pharma_box.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping
    public MedicineDto create(@Valid @RequestBody CreateMedicineDto dto) {
        return medicineService.create(dto);
    }

    @GetMapping("/user/{userId}")
    public List<MedicineDto> getByUser(@PathVariable UUID userId) {
        return medicineService.getAllByUser(userId);
    }

    @GetMapping("/{id}")
    public MedicineDto getById(@PathVariable UUID id) {
        return medicineService.getById(id);
    }

    @PutMapping("/{id}")
    public MedicineDto update(@PathVariable UUID id, @Valid @RequestBody MedicineDto dto) {
        return medicineService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        medicineService.delete(id);
    }
}
