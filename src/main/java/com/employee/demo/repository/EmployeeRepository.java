package com.employee.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee save(EmployeeRequest employeeRequest);
	// 1. Find the Top 3 Highest Paid Employees in Each Department
	@Query("""
		    SELECT e FROM Employee e 
		    WHERE e.salary IN (
		        SELECT e2.salary FROM Employee e2 
		        WHERE e2.department = e.department 
		        AND e2.salary >= ALL (
		            SELECT e3.salary FROM Employee e3 
		            WHERE e3.department = e2.department 
		            ORDER BY e3.salary DESC
		        )
		    )
		""")
		List<Employee> findTop3HighestPaidEmployeesInEachDepartment();

    // 2. Find Employees with the Second-Highest Salary
	@Query("""
		    SELECT e FROM Employee e 
		    WHERE e.salary = (
		        SELECT MAX(e2.salary) 
		        FROM Employee e2 
		        WHERE e2.salary < (SELECT MAX(e3.salary) FROM Employee e3)
		    )
		""")
		List<Employee> findEmployeesWithSecondHighestSalary();

    // 3. Find the Department with the Highest Total Salary
	@Query("""
		    SELECT e.department 
		    FROM Employee e 
		    GROUP BY e.department 
		    HAVING SUM(e.salary) = (
		        SELECT MAX(totalSalary) 
		        FROM (SELECT SUM(e2.salary) AS totalSalary FROM Employee e2 GROUP BY e2.department)
		    )
		""")
		String findDepartmentWithHighestTotalSalary();


    // 4. Find Employees Who Earn More Than Their Department's Average Salary
	@Query("""
		    SELECT e FROM Employee e 
		    WHERE e.salary > (SELECT AVG(e2.salary) FROM Employee e2 WHERE e2.department = e.department)
		""")
		List<Employee> findEmployeesEarningMoreThanDepartmentAverage();

    // 5. Find the Most Common First Letter in Employee Names
	@Query("""
		    SELECT SUBSTRING(e.name, 1, 1) 
		    FROM Employee e 
		    GROUP BY SUBSTRING(e.name, 1, 1) 
		    HAVING COUNT(e) = (
		        SELECT MAX(letter_count) 
		        FROM (SELECT COUNT(e2) AS letter_count FROM Employee e2 GROUP BY SUBSTRING(e2.name, 1, 1))
		    )
		""")
		String findMostCommonFirstLetter();

	}
