package com.example.prescriptionapi.service;


import com.example.prescriptionapi.exception.InformationExistException;
import com.example.prescriptionapi.exception.InformationNotFoundException;
import com.example.prescriptionapi.model.Patient;
import com.example.prescriptionapi.model.Prescription;
import com.example.prescriptionapi.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    PrescriptionRepository prescriptionRepository;

    @Autowired
    public void setPrescriptionRepository(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @PostMapping(path = "/patient/prescriptions")
    public Prescription createPrescription(Prescription prescriptionObject) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionObject.getId());
        if (prescription.isEmpty()) {
            throw new InformationExistException("Prescription with id number " + prescriptionObject.getId() + " already exists.");
        }else {
            return prescriptionRepository.save(prescriptionObject);
        }
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Prescription getPrescription(Long prescriptionId) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionId);
        if (prescription.isPresent()) {
            return prescription.get();
        } else {
            throw new InformationNotFoundException("Prescription with id " + prescriptionId + " does not exist.");
        }
    }



}
