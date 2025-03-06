package com.employee.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.employee.demo.model.Employee;

public interface EmployeeRepositoryPagination extends JpaRepository<Employee, Long> {

	 @Query("SELECT e FROM Employee e WHERE " +
	           "(:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%',:name,'%'))) AND " +
	           "(:id IS NULL OR e.id = :id) AND " +
	           "(:salary IS NULL OR e.salary = :salary) AND " +
	           "(:department IS NULL OR LOWER(e.department) LIKE LOWER(CONCAT('%',:department,'%')))")
	
	Page<Employee> searchEmployees(
			@Param("name") String name,
			@Param("id") Long id,
			@Param("salary") Double salary,
			@Param("department") String department,
			Pageable pageable);
}
