package com.employee.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employee.demo.model.Employee;

@Repository
public interface employeeRepository extends JpaRepository<Employee, Long> {
	// find by department
	List<Employee> findByDepartment(String department);

	// find by name
	Optional<Employee> findByName(String name);

}
