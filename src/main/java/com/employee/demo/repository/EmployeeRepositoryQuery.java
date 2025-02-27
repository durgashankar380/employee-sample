package com.employee.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.demo.model.Employee;

public interface EmployeeRepositoryQuery extends JpaRepository<Employee, Long> {
		@Query("SELECT e.department, SUM(e.salary) from Employee e GROUP BY e.department")
		List<Object[]> getTotalSalaryPerDepartment(); 
		
		@Query("SELECT DISTINCT(e.department) FROM Employee e")
		List<String> getUniqueEmployeeDepartments();

		@Query("SELECT  e.id, e.name, e.department, e.salary FROM Employee e")
		List<Object[]> getEmployeeGroupByDepartment();

		@Query("SELECT e.id, e.name, e.department, e.salary FROM Employee e")
		List<Object[]> getEmployeeByIdMap();

		@Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
		List<Employee> getEmployeeSortBySalary();
		
		@Query("SELECT e.department, COUNT(e.name) FROM Employee e GROUP BY e.department")
		List<Object[]> getDepartmentEmployeeCount();

		@Query("SELECT e.name FROM Employee e")
		List<String> getAllEmployeeName();

		@Query("SELECT e FROM Employee e ORDER BY e.id ASC")
		List<Employee> getEmployeeFirstInFirstOut();

		@Query("SELECT e from Employee e ORDER BY e.id DESC")
		List<Employee> getEmployeeLastInFirstOut();

		@Query("SELECT e FROM Employee e ORDER BY e.salary DESC LIMIT 1 OFFSET 1")
        List<Employee> getSecondHighestSalary();

		@Query("SELECT e.department, e.name, e.salary FROM Employee e WHERE e.salary > (SELECT AVG(e2.salary) FROM Employee e2 WHERE e2.department=e.department)")
		List<Object[]> getEmployeeEarnAboveDepartmentAverageSalary();

		@Query("SELECT e.department FROM Employee e GROUP BY e.department ORDER BY SUM(e.salary) DESC LIMIT 1")
		List<String> getDepartmentWithHighestTotalSalary();

		@Query("SELECT SUBSTRING(name, 1, 1) AS first_letter FROM Employee GROUP BY first_letter ORDER BY count(*) DESC LIMIT 1")
		List<String> mostCommonFirstLetterinEmployeeNames();

		@Query("SELECT e FROM Employee e WHERE e.salary IN (SELECT es.salary FROM Employee es WHERE es.department = e.department) ORDER BY e.salary DESC")
		List<Employee> getTop3HighestPaidEmployeePerDepartment();
		
}
