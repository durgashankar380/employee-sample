package com.employee.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
////
//public class Department {
//
//    @Id
//    private Long id;
//    private String name;
//    private String location;
//    private String description;
//
//    @OneToMany(mappedBy = "departments", cascade = CascadeType.ALL, orphanRemoval = true)
//
//    private List<Employee> employees;
//}
