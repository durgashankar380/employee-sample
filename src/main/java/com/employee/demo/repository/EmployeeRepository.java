package com.employee.demo.repository;

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
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO final_user (employee_id,name, department, salary) VALUES (:employeeId, :name, :department, :salary)", nativeQuery = true)
	void insertEmployee(long employeeId,String name, String department, double salary);

	@Query(nativeQuery = true,value="select * from final_user")
	List<Employee> getAllEmployee();

	@Query(value="select department , sum(salary) as totalSalary from final_user group by department",nativeQuery = true)
	public List<Object> getTotalSalaryByDepartment();

	@Query(nativeQuery = true,value = "select * from final_user where name=")
	 Employee findByName(String name);

	@Query(nativeQuery = true,value="select name from final_user")
	List<String> getAllNames();

	@Query(value = "SELECT u.* FROM final_user u WHERE u.employee_id IN (SELECT employee_id FROM (SELECT employee_id, department, salary, ROW_NUMBER() OVER (PARTITION BY department ORDER BY salary DESC) as rn FROM final_user) sub WHERE rn <= 3)", nativeQuery = true)
	List<Employee> findTop3HighestPaidEmployeesInEachDepartment();

	@Query(nativeQuery = true,value ="select * from final_user ")
	List<Employee> getallEmp();

	@Query(nativeQuery = true,value="select distinct(department) from final_user")
	Set<String> getUniqueEmployeeDepartments();

	@Query(nativeQuery = true,value = "select * from final_user order by salary desc")
	List<Employee> sortedBySalary();

	@Query(nativeQuery = true,value = "select * from final_user")
	List<Employee> QueueOfEmp();

	Employee save(EmployeeRequest employeeRequest);

	@Query(nativeQuery = true,value = "select * from final_user order by employee_id desc")
	List<Employee> StackOfEmp();

	@Query(value = "SELECT department FROM final_user GROUP BY department ORDER BY SUM(salary) DESC LIMIT 1", nativeQuery = true)
	String findDepartmentWithHighestTotalSalary();

	@Query(value = "SELECT * FROM final_user e WHERE e.salary > (SELECT AVG(e2.salary) FROM final_user e2 WHERE e2.department = e.department)", nativeQuery = true)
	List<Employee> findEmployeesAboveDepartmentAverageSalary();

	@Query(value = "SELECT LEFT(name, 1) as first_letter, COUNT(*) as count FROM final_user GROUP BY first_letter ORDER BY count DESC LIMIT 1", nativeQuery = true)
	List<Object[]> findMostCommonFirstLetter();

	@Query(value = "SELECT * FROM final_user e WHERE e.salary = (SELECT MAX(e2.salary) FROM final_user e2 WHERE e2.salary < (SELECT MAX(e3.salary) FROM final_user e3))", nativeQuery = true)
	List<Employee> findEmployeesWithSecondHighestSalary();

	@Query(value = "SELECT employee_id, name, department, salary FROM final_user", nativeQuery = true)
	List<Object[]> findAllEmployeesForGrouping();

	@Query(value = "SELECT employee_id, name, department, salary FROM final_user WHERE employee_id = :id", nativeQuery = true)
	List<Object[]> findEmployeeByIdForMap(@Param("id") Long id);

	@Query(value = "SELECT department, COUNT(*) FROM final_user GROUP BY department", nativeQuery = true)
	List<Object[]> getCountPerDepartmentNative();


	@Modifying
	@Transactional
	@Query(value = "UPDATE final_user SET name = :name, department = :department, salary = :salary WHERE employee_id = :id", nativeQuery = true)
	int updateEmployeeByIdNative(long id, String name, String department, double salary);



}
