package com.employee.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;
    
    @Column(name = "salary")
    private Double salary;
    
    @Column(name= "status")
    private int status;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "password")
    private String password;
    
}
