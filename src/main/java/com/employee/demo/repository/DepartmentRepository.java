package com.employee.demo.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentRequest;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

	Optional<Department> findById(int id);

	Page<Department> findAll(Specification<Department> spec, Pageable pageable);

	
}
