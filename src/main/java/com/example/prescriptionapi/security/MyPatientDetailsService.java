package com.example.prescriptionapi.security;

import com.example.prescriptionapi.model.Patient;
import com.example.prescriptionapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyPatientDetailsService implements UserDetailsService {

    private PatientService patientService;

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public UserDetails loadUserByUsername(String SSN) throws UsernameNotFoundException {
        Patient patient = patientService.findPatientBySSN(SSN);
        return new MyPatientDetails(patient);
    }
}
