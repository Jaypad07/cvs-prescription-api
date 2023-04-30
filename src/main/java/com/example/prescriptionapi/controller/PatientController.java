package com.example.prescriptionapi.controller;

import com.example.prescriptionapi.exception.InformationExistException;
import com.example.prescriptionapi.exception.InformationNotFoundException;
import com.example.prescriptionapi.model.Patient;
import com.example.prescriptionapi.repository.PatientRepository;
import com.example.prescriptionapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class PatientController {
   private PatientService patientService;

   @Autowired
   public void setPatientService(PatientService patientService) {
       this.patientService = patientService;
   }

    // http://localhost:9097/api/patient
    @GetMapping(path = "/patient")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    // http://localhost:9097/api/patient/1
    @GetMapping(path = "/patient/{patientId}")
    public Patient getPatient(@PathVariable Long patientId) {
        return patientService.getPatient(patientId);
    }

    // http://localhost:9097/api/patient
    @PostMapping(path = "/patient")
    public Patient createPatient(@RequestBody Patient patientObject) {
        return patientService.createPatient(patientObject);
    }
//
//    // http://localhost:9097/api/patient/1
//    @PutMapping(path = "/patient/{patientId}")
//    public Patient updatePatient(@PathVariable Long patientId, @RequestBody Patient patientObject) {
//        Patient patient = getPatient(patientId);
//        patient.setName(patientObject.getName());
//        patient.setAddress(patientObject.getAddress());
//        patient.setPhoneNumber(patientObject.getPhoneNumber());
//        patient.setEmailAddress(patientObject.getEmailAddress());
//        patient.setSocialSecurity(patient.getSocialSecurity());
//        return patientRepository.save(patient);
//    }
//
//    // http://localhost:9097/api/patient/1
//    @DeleteMapping(path = "/patient/{patientId}")
//    public Patient deletePatient(@PathVariable Long patientId) {
//        Patient patient = getPatient(patientId);
//        patientRepository.deleteById(patientId);
//        return patient;
//    }
}
