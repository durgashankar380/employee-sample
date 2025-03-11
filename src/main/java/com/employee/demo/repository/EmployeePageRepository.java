package com.employee.demo.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.demo.model.Employee;

@Repository
public interface EmployeePageRepository extends JpaRepository<Employee, Long> {
  
    @Query("SELECT e FROM Employee e WHERE " +
          "(:keyword IS NULL OR :keyword = '' OR " +
          "CAST(e.id AS string) LIKE CONCAT('%', :keyword, '%') OR " +
          "e.name LIKE CONCAT('%', :keyword, '%') OR " +
          "e.department LIKE CONCAT('%', :keyword, '%') OR " +
          "CAST(e.salary AS string) LIKE CONCAT('%', :keyword, '%'))")
    Page<Employee> searchEmployees(@Param("keyword") String keyword, Pageable pageable);
    
    
    Page<Employee> findByStatus(int status, Pageable pageable);

    Page<Employee> findByStatusNot(int status, Pageable pageable);
}
  
