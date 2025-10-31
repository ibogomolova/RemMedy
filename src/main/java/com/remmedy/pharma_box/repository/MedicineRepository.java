package com.remmedy.pharma_box.repository;

import com.remmedy.pharma_box.model.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID> {

    Page<Medicine> findByUserId(UUID userId, Pageable pageable);

    @Query(value = """
                    SELECT *
                    FROM medicines AS m
                    WHERE m.id = :medicineId
                    FOR UPDATE;
            """, nativeQuery = true)
    Optional<Medicine> findMedicineByIdForUpdate(UUID medicineId);
}
