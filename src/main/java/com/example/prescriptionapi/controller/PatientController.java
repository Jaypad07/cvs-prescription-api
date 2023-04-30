package com.example.prescriptionapi.controller;

import com.example.prescriptionapi.exception.InformationExistException;
import com.example.prescriptionapi.exception.InformationNotFoundException;
import com.example.prescriptionapi.model.Patient;
import com.example.prescriptionapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class PatientController {
   private PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // http://localhost:9097/api/patient
    @GetMapping(path = "/patient")
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    // http://localhost:9097/api/patient/1
    @GetMapping(path = "/patient/{patientId}")
    public Patient getPatient(@PathVariable Long patientId)    {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isEmpty()) {
            throw new InformationNotFoundException("Patient with id " + patientId + " does not exist.");
        }else return patient.get();
    }

    // http://localhost:9097/api/patient
    @PostMapping(path = "/patient")
    public Patient createPatient(@RequestBody Patient patientObject) {
        Optional<Patient> patient = patientRepository.findPatientBySocialSecurity(patientObject.getSocialSecurity());
        if (patient.isPresent()) {
            throw new InformationExistException("Patient with socialSecurity " + patientObject.getSocialSecurity() + " already exists");
        }else return patientRepository.save(patientObject);
    }

    // http://localhost:9097/api/patient/1
    @PutMapping(path = "/patient/{patientId}")
    public Patient updatePatient(@PathVariable Long patientId, @RequestBody Patient patientObject) {
        Patient patient = getPatient(patientId);
        patient.setName(patientObject.getName());
        patient.setAddress(patientObject.getAddress());
        patient.setPhoneNumber(patientObject.getPhoneNumber());
        patient.setEmailAddress(patientObject.getEmailAddress());
        patient.setSocialSecurity(patient.getSocialSecurity());
        return patientRepository.save(patient);
    }

    // http://localhost:9097/api/patient/1
    @DeleteMapping(path = "/patient/{patientId}")
    public Patient deletePatient(@PathVariable Long patientId) {
        Patient patient = getPatient(patientId);
        patientRepository.deleteById(patientId);
        return patient;
    }
}
