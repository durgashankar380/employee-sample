package com.employee.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department") 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Department {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name="name")
	    private String name;

        @Column(name="location")
	    private String location;
       
        @Column(name="description")
	    private String description;

	    @JsonManagedReference
	    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Employee> employees;
}
