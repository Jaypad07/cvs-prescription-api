package com.example.prescriptionapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "medications")
public class Medication {

        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column
        private String name;
        @Column
        private String description;
        @Column
        private Double dosage;
        @Column
        private Long quantity;
        @Column
        private Long refills;
        @Column
        private String sideEffects;

        //Many Medications can belong to Many Prescriptions
        @JsonIgnore
        @ManyToMany(mappedBy = "medicationList")
        private List<Prescription> prescriptionList;


        public Medication() {
        }

        public Medication(Long id, String name, String description, Double dosage, Long quantity, Long refills, String sideEffects) {
                this.id = id;
                this.name = name;
                this.description = description;
                this.dosage = dosage;
                this.quantity = quantity;
                this.refills = refills;
                this.sideEffects = sideEffects;
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

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
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

        public String getSideEffects() {
                return sideEffects;
        }

        public void setSideEffects(String sideEffects) {
                this.sideEffects = sideEffects;
        }

        public List<Prescription> getPrescriptionList() {
                return prescriptionList;
        }

        public void setPrescriptionList(List<Prescription> prescriptionList) {
                this.prescriptionList = prescriptionList;
        }

        @Override
        public String toString() {
                return "Medication{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", description='" + description + '\'' +
                        ", dosage=" + dosage +
                        ", quantity=" + quantity +
                        ", refills=" + refills +
                        ", sideEffects='" + sideEffects + '\'' +
                        '}';
        }
}
