package com.employee.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentRequestDto;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

	Optional<Department> findByName(String name);

	Department save(DepartmentRequestDto departmentRequestDto);

}
