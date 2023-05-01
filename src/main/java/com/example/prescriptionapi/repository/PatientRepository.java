package com.example.prescriptionapi.repository;

import com.example.prescriptionapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    //Find patient by SSN
    Optional<Patient> findPatientBySocialSecurity(String ssn);

    // Register new user
    boolean existsByEmailAddress(String emailAddress);

    // Login existing user
    Patient findUserByEmailAddress(String emailAddress);

}
