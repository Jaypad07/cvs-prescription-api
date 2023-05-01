package com.example.prescriptionapi.repository;

import com.example.prescriptionapi.model.Medication;
import com.example.prescriptionapi.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrescriptionRepository  extends JpaRepository<Prescription, Long> {
    boolean findByPatientIdAndMedicationListContainingIgnoreCase(Long id, Medication medication);

    Optional<Prescription> findByMedication(String name);

}
