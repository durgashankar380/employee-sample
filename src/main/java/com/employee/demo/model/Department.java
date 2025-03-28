package com.employee.demo.model;

import java.util.List;

import com.employee.demo.request.RequestEmployee;
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
        
        @Column(name= "status")
        private int status = 1;

	    @JsonManagedReference
	    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	    private List<Employee> employees;
}
