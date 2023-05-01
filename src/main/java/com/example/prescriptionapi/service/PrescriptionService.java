package com.example.prescriptionapi.service;

import com.example.prescriptionapi.exception.InformationNotFoundException;
import com.example.prescriptionapi.model.Prescription;
import com.example.prescriptionapi.repository.PatientRepository;
import com.example.prescriptionapi.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private PrescriptionRepository prescriptionRepository;

    private PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired
    public void setPrescriptionRepository(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

//    public Prescription createPrescription(Long patientId, Prescription prescriptionObject, Medication medication) {
//        Patient patient = patientRepository.findById(patientId).get();
//        if (patient.getPrescriptionList().contains(prescriptionObject)) {
//            throw new InformationExistException("Patient already has a prescription with medication: " + medication.getName());
//        } else {
//            patient.getPrescriptionList().add(prescriptionObject);
////            prescriptionObject.getMedicationList().add(medication);
//            return prescriptionRepository.save(prescriptionObject);
//        }
//    }


    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Prescription getPrescription(Long prescriptionId) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionId);
        if (prescription.isPresent()) {
            return prescription.get();
        } else {
            throw new InformationNotFoundException("Prescription with id " + prescriptionId + " does not exist.");
        }
    }

    public Prescription updatePrescription(Long id, Prescription prescription) {
            Prescription existingPrescription = getPrescription(id);
            existingPrescription.setPrescriber(prescription.getPrescriber());
            existingPrescription.setMedication(prescription.getMedication());
            existingPrescription.setDosage(prescription.getDosage());
            existingPrescription.setQuantity(prescription.getQuantity());
            existingPrescription.setRefills(prescription.getRefills());
            existingPrescription.setStartDate(prescription.getStartDate());
            existingPrescription.setEndDate(prescription.getEndDate());
            existingPrescription.setStatus(prescription.isStatus());
            return prescriptionRepository.save(existingPrescription);
        }


}
