package com.remmedy.pharma_box.repository;

import com.remmedy.pharma_box.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Query(value = """
                    SELECT *
                    FROM users AS u
                    WHERE u.id = :userId
                    FOR UPDATE;
            """, nativeQuery = true)
    Optional<User> findUserByIdForUpdate(UUID userId);
}
