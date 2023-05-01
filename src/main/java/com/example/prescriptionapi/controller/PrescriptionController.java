package com.example.prescriptionapi.controller;

import com.example.prescriptionapi.model.Prescription;
import com.example.prescriptionapi.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class PrescriptionController {

    private PrescriptionService prescriptionService;

    @Autowired
    public void setPrescriptionService(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // http://localhost:9097/api/prescription/12/lipson
//    @PostMapping(path = "/prescription/{id}/medications")
//    public Prescription createPrescription(@PathVariable Long id, @RequestBody Medication medication) {
//        return prescriptionService.createPrescription(id, medication);
//    }

    // http://localhost:9097/api/prescription
    @GetMapping(path = "/prescription")
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    // http://localhost:9097/api/prescription/1
    @GetMapping("/prescription/{id}")
    public Prescription getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescription(id);
    }

    // http://localhost:9097/api/prescription/1
    @PutMapping("prescription/{id}")
    public Prescription updatePrescription(@PathVariable Long id, @RequestBody Prescription prescriptionObject) {
        return prescriptionService.updatePrescription(id, prescriptionObject);
    }

    // http://localhost:9097/api/prescription/1
    @DeleteMapping("prescription/{id}")
    public Prescription DeletePrescription(@PathVariable Long id) {
        return prescriptionService.deletePrescription(id);
    }

}
