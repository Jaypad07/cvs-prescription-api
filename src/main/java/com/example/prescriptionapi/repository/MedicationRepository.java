package com.example.prescriptionapi.repository;

import com.example.prescriptionapi.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
