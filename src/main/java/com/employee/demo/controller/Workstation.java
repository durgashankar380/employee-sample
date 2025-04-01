package com.employee.demo.controller;

import com.employee.demo.model.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Workstation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String computerType;
    private String deskNumber;
    private String assignment;

    @OneToOne
    @JoinColumn(name = "employee_id",nullable = false,unique = true)
    @JsonBackReference

    private Employee employee;



}
