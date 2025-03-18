package com.employee.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.demo.model.Employee;
import com.employee.demo.request.RequestEmployee;

public interface EmployeeJwtRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

	void save(RequestEmployee employee);
}
