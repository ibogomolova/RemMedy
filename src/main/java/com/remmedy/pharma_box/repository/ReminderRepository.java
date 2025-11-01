package com.remmedy.pharma_box.repository;

import com.remmedy.pharma_box.model.Medicine;
import com.remmedy.pharma_box.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, UUID> {

    boolean existsByMedicineAndSentTrue(Medicine medicine);
}
