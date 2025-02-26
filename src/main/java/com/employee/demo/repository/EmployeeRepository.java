package com.employee.demo.repository;

import com.employee.demo.response.EmployeeResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee save(EmployeeRequest employeeRequest);

	List<Employee> findBySalary(double secMaxSalary);
    @Query("SELECT e FROM Employee e")
	List<Employee> getAllEmp();
	@Query("SELECT e FROM Employee e  WHERE salary = ( SELECT MAX(e2.salary) FROM Employee e2 WHERE e2.salary < (SELECT MAX(e3.salary) FROM Employee e3))")
	List<Employee> secondHighestSalary();
    @Query(nativeQuery = true,value = "SELECT * FROM Employee ORDER BY salary DESC LIMIT 3")
	List<Employee> topThreeHighestSalary();
    @Query(nativeQuery = true,value = "SELECT department FROM Employee GROUP BY department ORDER BY SUM(salary) DESC LIMIT 1  ")
	String getDepartmentWithHighestSalary();
	@Query(nativeQuery = true,value = "SELECT * FROM Employee as e1 WHERE e1.salary>(SELECT AVG(salary) FROM Employee as e2 WHERE e1.department=e2.department)")
	List<EmployeeResponse> getEmpEarnMoreThanAvgSalary();
    @Query(nativeQuery = true,value = "SELECT department , COUNT(department) FROM Employee GROUP BY department ")
	List<Object[]> getCountPerDepartment();
    @Query(nativeQuery = true,value = "SELECT department , SUM(salary) FROM Employee GROUP BY department")
	List<Object[]> getTotalSalaryPerDepartment();
    @Query(nativeQuery = true,value = " select department from Employee group by department" )
	Set<String> getUniqueEmployeeDepartments();
    @Query(nativeQuery = true,value="select * from employee order by salary desc")
	List<Employee> getSortedSalaryDesc();
    @Query(nativeQuery = true,value = "select name from employee ")
	List<String> getEmployeesList();
    @Query(nativeQuery = true,value = "select left(name,1) as first_letter FROM employee group by first_letter order by count(first_letter) desc limit 1")
	char getMostCommonLetter();

	@Transactional
	@Modifying
    @Query(nativeQuery = true,value="insert into employee(name,department,salary) values (:name,:department,:salary) ")
	Employee addEmployee(@Param("name") String name,@Param("department") String department,@Param("salary") double salary);
}
