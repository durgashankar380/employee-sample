package com.employee.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.demo.model.Employee;

@Repository
public interface EmployeePaginationRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE " +
		       "LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(e.department) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Employee> findEmployee(String keyword);

}
