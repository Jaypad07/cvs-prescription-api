package com.example.prescriptionapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String Address;
    @Column
    private String phoneNumber;
    @Column(unique = true)
    private String socialSecurity;
    @Column
    private String emailAddress;
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // Only allowed to write to the field not retrieve the field. So others won't have access to user password.
    private String password;


    // One user can have many prescriptions
    @OneToMany(mappedBy = "patient")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Prescription> prescriptionList;

    @PrePersist
    @PreUpdate
    private void validateSSN() {
        if (socialSecurity != null) {
            if (!socialSecurity.matches("^\\d{3}-\\d{2}-\\d{4}$")){
                throw new IllegalArgumentException("Invalid social security number");
            }
        }
    }

    public Patient() {
    }

    public Patient(Long id, String name, String address, String phoneNumber, String socialSecurity, String emailAddress, String password) {
        this.id = id;
        this.name = name;
        Address = address;
        this.phoneNumber = phoneNumber;
        this.socialSecurity = socialSecurity;
        this.emailAddress = emailAddress;
        this.password = password;
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

    public void setName(String userName) {
        this.name = userName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Address='" + Address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", socialSecurity=" + socialSecurity +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", prescriptionList=" + prescriptionList +
                '}';
    }
}
