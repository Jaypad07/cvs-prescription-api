package com.example.prescriptionapi.service;

import com.example.prescriptionapi.exception.InformationNotFoundException;
import com.example.prescriptionapi.model.Medication;
import com.example.prescriptionapi.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {

    private MedicationRepository medicationRepository;

    @Autowired
    public void setMedicationRepository(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    public Optional<Medication> getMedicationById(Long id) {
        Optional<Medication> medication = medicationRepository.findById(id);
        if (medication.isPresent()) {
            return medication;
        }else {
            throw new InformationNotFoundException("Medication with id " + id + " does not exist.");
        }
    }

    public Medication createMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    public Medication updateMedication(Long id, Medication medication) {
        Optional<Medication> existingMedication = medicationRepository.findById(id);
        if (existingMedication.isPresent()) {
            Medication updatedMedication = existingMedication.get();
            updatedMedication.setName(medication.getName());
            updatedMedication.setDescription(medication.getDescription());
            return medicationRepository.save(updatedMedication);
        } else {
            return null;
        }
    }

    public Medication deleteMedication(Long id) {
        Medication medication = getMedicationById(id).get();
        medicationRepository.deleteById(id);
        return medication;
    }
}
