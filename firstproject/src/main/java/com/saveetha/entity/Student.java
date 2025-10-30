package com.saveetha.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    private String name;
    private String branch;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

}
