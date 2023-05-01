package com.example.prescriptionapi.controller;

import com.example.prescriptionapi.model.Prescription;
import com.example.prescriptionapi.repository.PrescriptionRepository;
import com.example.prescriptionapi.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class PrescriptionController {

    private PrescriptionService prescriptionService;

    @Autowired
    public void setPrescriptionService(PrescriptionService prescriptionService) {

    }

    private PrescriptionRepository prescriptionRepository;

    @Autowired
    public void setPrescriptionRepository(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }


    // http://localhost:9097/api/prescriptions
    @PostMapping(path = "/patient/prescriptions")
    public Prescription createPrescriptions(@RequestBody Prescription prescriptionObject) {
        return prescriptionService.createPrescription(prescriptionObject);
    }

    // http://localhost:9097/api/prescriptions
    @GetMapping(path = "/prescriptions")
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    // http://localhost:9097/api/prescriptions/1
    @GetMapping("/prescriptions/{id}")
    public Prescription getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescription(id);
    }




}
