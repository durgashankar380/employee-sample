package com.employee.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
	
	}
