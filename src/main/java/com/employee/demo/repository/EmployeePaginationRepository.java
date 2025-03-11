package com.employee.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.demo.model.Employee;

@Repository
public interface EmployeePaginationRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE " +
	           "(:salary IS NULL OR e.salary = :salary) AND " +
	           "(:keyword IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	Page<Employee> searchEmployees(@Param("salary") Double salary, 
	                                   @Param("keyword") String keyword, 
	                                   Pageable pageable);

	
	Page<Employee> findByStatus(int status, Pageable pageable);
	   
	Page<Employee> findByStatusNot(int status,  Pageable pageable);


	//Page<Employee> getEmployees(int status, Pageable pageable);
}
