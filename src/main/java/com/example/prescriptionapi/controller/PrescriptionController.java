package com.example.prescriptionapi.controller;

import com.example.prescriptionapi.model.Prescription;
import com.example.prescriptionapi.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/patient/")
public class PrescriptionController {

    private PrescriptionService prescriptionService;

    @Autowired
    public void setPrescriptionService(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // http://localhost:9097/api/prescription
    @PostMapping(path = "/patient/prescription")
    public Prescription createPrescriptions(@RequestBody Prescription prescriptionObject) {
        return prescriptionService.createPrescription(prescriptionObject);
    }

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

}
