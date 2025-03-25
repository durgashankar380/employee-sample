package com.employee.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
    public Employee(Object object, String name2, Double salary2) {
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;

//    @Column(name = "department")
//    private String department;
    
    @Column(name = "salary")
    private Double salary;
    
    @Column(name= "status")
    private int status = 1;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "password")
    private String password;
    
  
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
