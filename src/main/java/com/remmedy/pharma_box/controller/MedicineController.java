package com.remmedy.pharma_box.controller;

import com.remmedy.pharma_box.dto.CreateMedicineDto;
import com.remmedy.pharma_box.dto.MedicineDto;
import com.remmedy.pharma_box.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping
    public ResponseEntity<MedicineDto> create(@Valid @RequestBody CreateMedicineDto dto) {
        return ResponseEntity.ok(medicineService.create(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MedicineDto>> getByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(medicineService.getAllByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(medicineService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineDto> update(@PathVariable UUID id, @Valid @RequestBody MedicineDto dto) {
        return ResponseEntity.ok(medicineService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        medicineService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
