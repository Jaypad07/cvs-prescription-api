package com.example.prescriptionapi.model;

import javax.persistence.Entity;

@Entity
public class User {
    private Long id;
    private String userName;
    private String phoneNumber;
    private String emailAddress;
    private String password;
}
