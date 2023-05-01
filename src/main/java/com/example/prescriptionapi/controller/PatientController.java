package com.example.prescriptionapi.controller;

import com.example.prescriptionapi.model.Patient;
import com.example.prescriptionapi.model.Prescription;
import com.example.prescriptionapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // http://localhost:9097/api/patient/1/prescriptions
    @PostMapping(path = "/patient/{patientId}/prescriptions")
    public Prescription createPrescriptionForPatient(@PathVariable Long patientId, @RequestBody Prescription prescriptionObject){
       return patientService.createPrescriptionForPatient(patientId, prescriptionObject);
    }



    // http://localhost:9097/api/patient/1
    @PutMapping(path = "/patient/{patientId}")
    public Patient updatePatient(@PathVariable Long patientId, @RequestBody Patient patientObject) {
        return patientService.updatePatient(patientId, patientObject);
    }

    // http://localhost:9097/api/patient/1
    @DeleteMapping(path = "/patient/{patientId}")
    public Patient deletePatient(@PathVariable Long patientId) {
        return patientService.deletePatient(patientId);
    }
}
