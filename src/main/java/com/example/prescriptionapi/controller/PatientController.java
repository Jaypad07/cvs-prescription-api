package com.example.prescriptionapi.controller;

import com.example.prescriptionapi.exception.InformationExistException;
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

    // http://localhost:9097/api/patient
    @PostMapping(path = "/patient")
    public Patient createPatient(@RequestBody Patient patientObject) {
        Optional<Patient> patient = patientRepository.findPatientBySocialSecurity(patientObject.getSocialSecurity());
        if (patient.isPresent()) {
            throw new InformationExistException("Patient with socialSecurity " + patientObject.getSocialSecurity() + " already exists");
        }else return patientRepository.save(patientObject);
    }

    // http://localhost:9097/api/patient/1
    @GetMapping(path = "/patient/{id}")
    public Patient findPatientId(@PathVariable Long id)    {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            throw new InformationExistException("Patient with id " + id + " already exists");
        }else return patient.get();
    }

//    // http://localhost:9097/api/patient/Jay
//    @GetMapping(path = "/patient/{name}")
//    public List<Patient> findPatientsByName(@PathVariable String name) {
//        return patientRepository.findPatientByNameContainingIgnoreCase(name);
//    }

}
