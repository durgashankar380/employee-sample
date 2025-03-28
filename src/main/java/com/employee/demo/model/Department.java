package com.employee.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Location")
	private String location;
	
	@Column(name = "Description")
	private String description;
	
	private int status=1;
	
	@OneToMany(mappedBy = "departments" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JsonIgnoreProperties("departments")
	private List<Employee> employee;
}
