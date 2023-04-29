package com.example.prescriptionapi.repository;

import com.example.prescriptionapi.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository  extends JpaRepository<Prescription, Long> {
//    Optional<Prescription> findByPatientAndMedication(String patient, S)
}
