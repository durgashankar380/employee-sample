package com.employee.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.demo.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Optional<Department> findByDepartmentName(String name);

	Optional<Department> findByDepartmentNameAndLocation(String departmentName, String location);

	Optional<Department> findByDepartmentId(long departmentId);

	@Query("SELECT d FROM Department d WHERE "
	        + "(:keyword IS NULL OR :keyword = '' OR LOWER(d.departmentName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
	        + "LOWER(d.location) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
	        + "AND (:status IS NULL OR (:status = 0 AND d.status IN (1, 2)) OR d.status = :status)")
	Page<Department> findDepartmentByKeywordAndStatus(
	        @Param("keyword") String keyword, 
	        @Param("status") Integer status, 
	        Pageable pageable);
}