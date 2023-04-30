package com.example.prescriptionapi.service;

import com.example.prescriptionapi.exception.InformationExistException;
import com.example.prescriptionapi.exception.InformationNotFoundException;
import com.example.prescriptionapi.model.Patient;
import com.example.prescriptionapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatient(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            return patient.get();
        } else {
            throw new InformationNotFoundException("Patient with id " + patientId + " does not exist.");
        }
    }

    public Patient createPatient(Patient patientObject) {
        Optional<Patient> patient = patientRepository.findPatientBySocialSecurity(patientObject.getSocialSecurity());
        if (patient.isPresent()) {
            throw new InformationExistException("Patient with socialSecurity " + patientObject.getSocialSecurity() + " already exists");
        } else {
            return patientRepository.save(patientObject);
        }
    }

    //Patient cannot have social edited. It will replace
    public Patient updatePatient(Long patientId, Patient patientObject) {
        Patient patient = getPatient(patientId);
        patient.setName(patientObject.getName());
        patient.setAddress(patientObject.getAddress());
        patient.setPhoneNumber(patientObject.getPhoneNumber());
        patient.setEmailAddress(patientObject.getEmailAddress());
        patient.setSocialSecurity(patient.getSocialSecurity());
        return patientRepository.save(patient);
    }

    public Patient deletePatient(Long patientId) {
        Patient patient = getPatient(patientId);
        patientRepository.deleteById(patientId);
        return patient;
    }
}


