package com.employee.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.demo.model.Employee;

public interface EmployeeJwtRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmailId(String emailId);

}
