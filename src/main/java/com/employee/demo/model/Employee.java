package com.employee.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;
    private double salary;
    
    private int status;
    
    @Column(unique = true, nullable = false)
    private String emailId;
    
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnoreProperties("employees")
    private Department departments;
}