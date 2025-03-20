package com.employee.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.demo.model.Employee;

@Repository
public interface EmployeeQueryRepository extends JpaRepository<Employee, Long> {

	@Query("select Sum(e.salary) from Employee e")
	Double findTotalSalary();

	@Query("select distinct(e.department) from Employee e")
	List<String> findUniqueDepartment();

	@Query("select e from Employee e order by e.salary desc")
	List<Employee> findSortedBySalary();

	@Query("select (e.name) from Employee e")
	List<String> findAllEmployeeName();

	@Query("select e from Employee e order by e.id desc")
	List<Employee> findAllByLatestOrder();

	@Query("select e from Employee e order by e.id asc")
	List<Employee> findAllByEarlestOrder();

	@Query("select e from Employee e where e.salary = (select max(e2.salary) from Employee e2 where e2.salary < (select max(e3.salary) from Employee e3))")
	List<Employee> findSecondHighestSalary();

	@Query("select e from Employee e")
	List<Employee> findAllEmployee();

	@Query("select e.department, count(e) from Employee e group by e.department")
	List<Object[]> getEmployeeCountInDepartment();

	@Query("select e.department, sum(e.salary) from Employee e group by e.department order by sum(e.salary) desc limit 1")
	List<Object[]> findHighestPaidDepartment();
     
	@Query("select e.id, e.name, e.department, e.salary, e.status from Employee e")
	List<Object[]> findEmployeeByDepartment();
	
	

}
