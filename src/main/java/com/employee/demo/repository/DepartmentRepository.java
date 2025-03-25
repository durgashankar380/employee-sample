package com.employee.demo.repository;

import com.employee.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Optional<Department> findByName(String name);

    Optional<Department> findByNameAndLocation(String name, String location);
}
