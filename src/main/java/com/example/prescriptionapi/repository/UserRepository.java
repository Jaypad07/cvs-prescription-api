package com.example.prescriptionapi.repository;

import com.example.prescriptionapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Register new user
    boolean existsByEmailAddress(String emailAddress);

    // Login existing user
    User findUserByEmailAddress(String emailAddress);
}
