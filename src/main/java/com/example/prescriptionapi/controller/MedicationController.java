package com.example.prescriptionapi.controller;

import com.example.prescriptionapi.model.Medication;
import com.example.prescriptionapi.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicationController {

    private MedicationService medicationService;

    @Autowired
    public void setMedicationService(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    // http://localhost:9097/api/medication
    @GetMapping(path = "/medication")
    public List<Medication> getAllMedications() {
        List<Medication> medications = medicationService.getAllMedications();
        return medications;
    }

    // http://localhost:9097/api/medication/1
    @GetMapping(path = "/medication/{medicationId}")
    public Medication getMedication(@PathVariable Long medicationId) {
        return medicationService.getMedicationById(medicationId).get();
    }

    // http://localhost:9097/api/medication
    @PostMapping(path = "/medication")
    public Medication createMedication(@RequestBody Medication medicationObject) {
        return medicationService.createMedication(medicationObject);
    }

    // http://localhost:9097/api/medication/1
    @PutMapping(path = "/medication/{medicationId}")
    public Medication updateMedication(@PathVariable Long medicationId, @RequestBody Medication medicationObject) {
        return medicationService.updateMedication(medicationId, medicationObject);
    }

    // http://localhost:9097/api/medication/1
    @DeleteMapping(path = "/medication/{medicationId}")
    public Medication deleteMedication(@PathVariable Long medicationId) {
        return medicationService.deleteMedication(medicationId);
    }
}

