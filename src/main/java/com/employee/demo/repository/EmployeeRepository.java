package com.employee.demo.repository;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee>findByDepartmentId(Long department);

    //
//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO employee (name, department, salary,status) VALUES (:name, :department, :salary, :status)", nativeQuery = true)
//    void insertEmployee(String name, String department, double salary, Integer status);
//
//    @Query(nativeQuery = true, value = "select * from employee")
//    List<Employee> getAllEmployee();
//
//    @Query(value = "select department , sum(salary) as totalSalary from employee group by department", nativeQuery = true)
//    public List<Object> getTotalSalaryByDepartment();
//
    @Query(nativeQuery = true, value = "select * from employee where name=")
    Employee findByName(String name);

//    @Query(nativeQuery = true, value = "select name from employee")
//    List<String> getAllNames();
//
//    @Query(value = "SELECT u.* FROM employee u WHERE u.id IN (SELECT employee_id FROM (SELECT id, department, salary, ROW_NUMBER() OVER (PARTITION BY department ORDER BY salary DESC) as rn FROM employee) sub WHERE rn <= 3)", nativeQuery = true)
//    List<Employee> findTop3HighestPaidEmployeesInEachDepartment();
//
//    @Query(nativeQuery = true, value = "select * from employee ")
//    List<Employee> getallEmp();
//
//    @Query(nativeQuery = true, value = "select distinct(department) from employee")
//    Set<String> getUniqueEmployeeDepartments();
//
//    @Query(nativeQuery = true, value = "select * from employee order by salary desc")
//    List<Employee> sortedBySalary();
//
//    @Query(nativeQuery = true, value = "select * from employee")
//    List<Employee> QueueOfEmp();
//
//    Employee save(EmployeeRequest employeeRequest);
//
//    @Query(nativeQuery = true, value = "select * from employee order by id desc")
//    List<Employee> StackOfEmp();
//
//    @Query(value = "SELECT department FROM employee GROUP BY department ORDER BY SUM(salary) DESC LIMIT 1", nativeQuery = true)
//    String findDepartmentWithHighestTotalSalary();
//
//    @Query(value = "SELECT * FROM employee e WHERE e.salary > (SELECT AVG(e2.salary) FROM employee e2 WHERE e2.department = e.department)", nativeQuery = true)
//    List<Employee> findEmployeesAboveDepartmentAverageSalary();
//
//    @Query(value = "SELECT LEFT(name, 1) as first_letter, COUNT(*) as count FROM employee GROUP BY first_letter ORDER BY count DESC LIMIT 1", nativeQuery = true)
//    List<Object[]> findMostCommonFirstLetter();
//
//    @Query(value = "SELECT * FROM employee e WHERE e.salary = (SELECT MAX(e2.salary) FROM employee e2 WHERE e2.salary < (SELECT MAX(e3.salary) FROM employee e3))", nativeQuery = true)
//    List<Employee> findEmployeesWithSecondHighestSalary();
//
//    @Query(value = "SELECT id, name, department, salary FROM employee", nativeQuery = true)
//    List<Object[]> findAllEmployeesForGrouping();
//
//    @Query(value = "SELECT id, name, department, salary FROM employee WHERE id = :id", nativeQuery = true)
//    List<Object[]> findEmployeeByIdForMap(@Param("id") Long id);
//
//    @Query(value = "SELECT department, COUNT(*) FROM employee GROUP BY department", nativeQuery = true)
//    List<Object[]> getCountPerDepartmentNative();
//
//
//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE employee SET name = :name, department = :department, salary = :salary, status = :status WHERE id = :id", nativeQuery = true)
//    int updateEmployeeByIdNative(long id, String name, String department, double salary, Integer status);
//
//
    @Query(nativeQuery = true, value = "SELECT * FROM employee WHERE " + "LOWER(name) LIKE LOWER(CONCAT('%', :search, '%')) OR " + "CAST(salary AS CHAR) LIKE CONCAT('%', :search, '%') OR " + "CAST(id AS CHAR) LIKE CONCAT('%', :search, '%') ")
    Page<Employee> search(@Param("search") String searchBy, Pageable pageable);

    Page<Employee> findByNameAndSalaryAndDepartment(String name, double salary, String department, Pageable pageable);

    Page<Employee> findByStatusNot(int i, Pageable pageable);

    Page<Employee> findByStatus(int status, Pageable pageable);

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartment(Department department);
}
