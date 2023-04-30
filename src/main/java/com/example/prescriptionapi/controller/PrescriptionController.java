package com.example.prescriptionapi.controller;

import com.example.prescriptionapi.model.Prescription;
import com.example.prescriptionapi.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class PrescriptionController {

////    private PrescriptionService prescriptionService;
//
//    @Autowired
//    public void setPrescriptionService(PrescriptionService prescriptionService) {
//
//    }

    private PrescriptionRepository prescriptionRepository;

    @Autowired
    public void setPrescriptionRepository(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    // http://localhost:9097/api/hello-world
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    // http://localhost:9097/api/prescriptions
//    @PostMapping(path = "/patient/prescriptions")
//    public List<Prescription> createPrescriptions(@RequestBody Prescription prescriptionObject) {
//        Optional<Prescription> prescription = prescriptionRepository.findByPatientSocialSecurityAndMedication(, prescriptionObject.getMedication());
//    }

}
