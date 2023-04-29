package com.example.prescriptionapi.repository;

import com.example.prescriptionapi.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrescriptionRepository  extends JpaRepository<Prescription, Long> {
    Optional<Prescription> findByPatientSocialSecurityAndMedication(Long patientId, String medication);

}
