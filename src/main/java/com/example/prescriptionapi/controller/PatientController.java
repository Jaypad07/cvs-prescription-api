package com.example.prescriptionapi.controller;

import com.example.prescriptionapi.exception.InformationExistException;
import com.example.prescriptionapi.model.Patient;
import com.example.prescriptionapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(path = "/patient")
    public Patient createPatient(@RequestBody Patient patientObject) {
        Optional<Patient> patient = patientRepository.findPatientByName(patientObject.getName());
        if (patient.isPresent()) {
            throw new InformationExistException("Patient with id " + patientObject.getId() + " already exists");
        }else return patientRepository.save(patientObject);
    }

}