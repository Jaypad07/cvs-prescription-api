package com.example.prescriptionapi.service;

import com.example.prescriptionapi.exception.InformationExistException;
import com.example.prescriptionapi.exception.InformationNotFoundException;
import com.example.prescriptionapi.model.Patient;
import com.example.prescriptionapi.model.Prescription;
import com.example.prescriptionapi.model.request.LoginRequest;
import com.example.prescriptionapi.model.response.LoginResponse;
import com.example.prescriptionapi.repository.PatientRepository;
import com.example.prescriptionapi.repository.PrescriptionRepository;
import com.example.prescriptionapi.security.JWTUtils;
import com.example.prescriptionapi.security.MyPatientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private PrescriptionRepository prescriptionRepository;
    private final PasswordEncoder passwordEncoder;
    private JWTUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    private MyPatientDetails patientDetails;

    public static Patient getLoggedInPatient() {
        MyPatientDetails PatientDetails = (MyPatientDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return PatientDetails.getPatient();
    }

    @Autowired
    public PatientService(PatientRepository patientRepository, @Lazy PasswordEncoder passwordEncoder, JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager, @Lazy MyPatientDetails patientDetails) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.patientDetails = patientDetails;
    }

    @Autowired
    public void setPrescriptionRepository(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patientObject) {
        if (!patientRepository.existsBySocialSecurity(patientObject.getSocialSecurity())) {
            patientObject.setPassword(passwordEncoder.encode(patientObject.getPassword()));
            return patientRepository.save(patientObject);
        } else {
            throw new InformationExistException("Email address " + patientObject.getSocialSecurity() + " already exists");
        }
    }

    public Patient findPatientBySSN(String ssn) {
        return patientRepository.findPatientBySocialSecurity(ssn).get();
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            patientDetails = (MyPatientDetails) authentication.getPrincipal();
            final String JWT = jwtUtils.generateJwtToken(patientDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.ok(new LoginResponse("Error : user name or password is incorrect"));
        }
    }


    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatient(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            return patient.get();
        } else {
            throw new InformationNotFoundException("Patient with id " + patientId + " does not exist.");
        }
    }

    public Patient createNewPatient(Patient patientObject) {
        Optional<Patient> patient = patientRepository.findPatientBySocialSecurity(patientObject.getSocialSecurity());
        if (patient.isPresent()) {
            throw new InformationExistException("Patient with socialSecurity " + patientObject.getSocialSecurity() + " already exists.");
        } else {
            return patientRepository.save(patientObject);
        }
    }

    public Prescription createPrescriptionForPatient(Long patientId, Prescription prescriptionObject) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            Optional<Prescription> prescription = prescriptionRepository.findByMedication(prescriptionObject.getMedication());

//            if (patient.get().getPrescriptionList().contains(prescriptionObject)) {
            if (prescription.isPresent()) {
                throw new InformationExistException("Patient already has that prescription: " + patient.get().getPrescriptionList());
            } else {
//                patient.get().getPrescriptionList().add(prescriptionObject);
                prescriptionObject.setPatient(patient.get());
//            prescriptionObject.getMedicationList().add(medication);
                return prescriptionRepository.save(prescriptionObject);
            }
        } else {
            throw new InformationNotFoundException("Patient not found");
        }
    }

    //Patient cannot have social edited. It will replace
    public Patient updatePatient(Long patientId, Patient patientObject) {
        Patient updatedPatient = getPatient(patientId);
        updatedPatient.setName(patientObject.getName());
        updatedPatient.setAddress(patientObject.getAddress());
        updatedPatient.setPhoneNumber(patientObject.getPhoneNumber());
        updatedPatient.setEmailAddress(patientObject.getEmailAddress());
        updatedPatient.setSocialSecurity(updatedPatient.getSocialSecurity());
        return patientRepository.save(updatedPatient);
    }

    public Patient deletePatient(Long patientId) {
        Patient patient = getPatient(patientId);
        patientRepository.deleteById(patientId);
        return patient;
    }
}


