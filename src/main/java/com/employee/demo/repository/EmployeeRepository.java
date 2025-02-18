package com.employee.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee save(EmployeeRequest employeeRequest);}
