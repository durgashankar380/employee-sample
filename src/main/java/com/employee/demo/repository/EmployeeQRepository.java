package com.employee.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.demo.model.Employee;
import com.employee.demo.response.ResponseEmployee;

public interface EmployeeQRepository extends JpaRepository<Employee, Long> {

    
    //1 sorted by salary
    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
    List<Employee> getEmployeesSortedBySalary();
    
    //2 get all unique departments
    @Query("SELECT DISTINCT e.department FROM Employee e")
    List<String> getUniqueDepartments();
    
    //3 get all employee names
    @Query("SELECT e.name FROM Employee e")
    List<String> getEmployeeNames();
    
    //4 get all employees in FIFO order
    @Query("SELECT e FROM Employee e ORDER BY e.id ASC")
    List<Employee> getEmployeesInFIFOOrder();
    
    //5 get all employees in LIFO order
    @Query("SELECT e FROM Employee e ORDER BY e.id DESC")
    List<Employee> getEmployeesInLIFOOrder();
    
    //6 get employee count per department
    @Query("SELECT e.department, COUNT(e) FROM Employee e GROUP BY e.department")
    List<Object[]> getEmployeeCountPerDepartment();

    
    //7 get employees grouped by department
    @Query("SELECT e FROM Employee e ORDER BY e.department")
    List<Employee> getEmployeesGroupedByDepartment();

    //8 get total salary per department
    @Query("SELECT e.department, SUM(e.salary) FROM Employee e GROUP BY e.department")
    List<Object[]> getTotalSalaryPerDepartment();
  
    //9 get employees As map
    @Query("SELECT e FROM Employee e")
    List<Employee> getAllEmployees();
    
    //10 get employees with second highest salary
    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC LIMIT 1 OFFSET 1")
    List<ResponseEmployee> getEmployeesWithSecondHighestSalary();
    
    //11 get department with highest total salary
    @Query("SELECT department, SUM(salary) AS total_salary FROM Employee GROUP BY department ORDER BY total_salary DESC LIMIT 1")
    String getDepartmentWithHighestTotalSalary();
    
    //12 get employee who have salary greater then its department average
    @Query("SELECT e FROM Employee e WHERE e.salary > (SELECT AVG(e2.salary) FROM Employee e2 WHERE e2.department = e.department)")
    List<Employee> findEmployeesAboveDepartmentAverage();
    
    //13 get top3 employee per department
    @Query("SELECT e FROM Employee e ORDER BY e.department, e.salary DESC")
    List<Employee> getTop3EmployeesByDepartment();
    
    //14 get most comman first letter from employees
    @Query("SELECT SUBSTRING(e.name, 1, 1) AS firstLetter, COUNT(e) AS count " +
            "FROM Employee e GROUP BY firstLetter ORDER BY count DESC")
     List<Object[]> getMostCommonFirstLetter();

	
}
