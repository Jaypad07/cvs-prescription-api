package com.example.prescriptionapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String prescriber;
    @Column
    private String medication;
    @Column
    private Double dosage;
    @Column
    private Long quantity;
    @Column
    private String refills;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column
    private boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToMany
    @JoinTable(name = "prescription_medication", joinColumns = @JoinColumn(name = "prescription_id"), inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private List<Medication> medicationList;

    public Prescription() {
    }

    public Prescription(Long id, String prescriber, String medication, Double dosage, Long quantity, String refills, LocalDate startDate, LocalDate endDate, boolean status) {
        this.id = id;
        this.prescriber = prescriber;
        this.medication = medication;
        this.dosage = dosage;
        this.quantity = quantity;
        this.refills = refills;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrescriber() {
        return prescriber;
    }

    public void setPrescriber(String prescriber) {
        this.prescriber = prescriber;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getRefills() {
        return refills;
    }

    public void setFrequency(String refills) {
        this.refills = refills;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", prescriber='" + prescriber + '\'' +
                ", patient='" + patient.getUserName() + '\'' +
                ", medication='" + medication + '\'' +
                ", dosage=" + dosage +
                ", quantity=" + quantity +
                ", refills='" + refills + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", user=" + patient +
                '}';
    }
}
