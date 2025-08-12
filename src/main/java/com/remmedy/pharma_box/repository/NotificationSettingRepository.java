package com.remmedy.pharma_box.repository;

import com.remmedy.pharma_box.entity.NotificationSetting;
import com.remmedy.pharma_box.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, UUID> {
    Optional<NotificationSetting> findByUser(User user);
}
