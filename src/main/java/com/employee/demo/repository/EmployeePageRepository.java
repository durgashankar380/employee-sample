package com.employee.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.demo.model.Employee;

@Repository
public interface EmployeePageRepository extends JpaRepository<Employee, Long> {

	Page<Employee> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

}
