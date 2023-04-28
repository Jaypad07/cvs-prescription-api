package com.example.prescriptionapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Double dosage;
    @Column
    private Long quantity;
    @Column
    private Long refills;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column
    private boolean activePrescription;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Prescription() {
    }

    public Prescription(Long id, String name, Double dosage, Long quantity, Long refills, LocalDate startDate, LocalDate endDate, boolean activePrescription) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.quantity = quantity;
        this.refills = refills;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activePrescription = activePrescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getRefills() {
        return refills;
    }

    public void setRefills(Long refills) {
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

    public boolean isActivePrescription() {
        return activePrescription;
    }

    public void setActivePrescription(boolean activePrescription) {
        this.activePrescription = activePrescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosage=" + dosage +
                ", quantity=" + quantity +
                ", refills=" + refills +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", activePrescription=" + activePrescription +
                ", user=" + user +
                '}';
    }
}
