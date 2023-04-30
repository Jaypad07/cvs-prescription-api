package com.example.prescriptionapi.repository;

import com.example.prescriptionapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Find user by name
    Optional<Patient> findPatientByName(String name);

    // Find user by Social
    Long findPatientBySocialSecurity(Long SSN);

    // Register new user
    boolean existsByEmailAddress(String emailAddress);

    // Login existing user
    Patient findUserByEmailAddress(String emailAddress);

}
