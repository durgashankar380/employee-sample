package com.employee.demo.repository;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

	Employee save(EmployeeRequest employeeRequest);
	public Employee findByName(String name);
	public List<Employee> findByDepartment(String department);




}
