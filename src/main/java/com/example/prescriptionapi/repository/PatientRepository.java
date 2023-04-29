package com.example.prescriptionapi.repository;

import com.example.prescriptionapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Register new user
    boolean existsByEmailAddress(String emailAddress);

    // Login existing user
    Patient findUserByEmailAddress(String emailAddress);

}
