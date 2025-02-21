package com.employee.demo.service;
//
//import io.MainPackage.Employee.Entity.EmployeeEntity;
//import io.MainPackage.Employee.RequestPayload.EmployeeRequest;
//import io.MainPackage.Employee.ResponsePayload.EmployeeResponse;
import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

public interface EmployeeService {

//    public List<EmployeeEntity> getAllEmployees();

	public List<Employee>stackOfEmployee();

	//public Queue<EmployeeResponse> getEmployeesAsQueue();

	public List<EmployeeResponse> getAllEmployees();


	Optional<Employee> getEmployeeById(Long id);

	Employee updateEmpById(long id, Employee employeeEntity);

	//     Employee addEmployee(Employee employee);
	Employee addEmployee(EmployeeRequest employee);


	Employee getEmployeeByName(String name);

	List<Employee> getAllEmployeeByDepartment(String department);

	ResponseEntity totalSalaryByDepartment(String dept);

	Map<String, Long> getcountperDepartment();

	ResponseEntity<Employee> addMultipleEmployees(List<Employee> employeeEntity);

	public List<String>getAllEmployeeName();

	Map<String,Double> getTotalSalaryByDepartment();


}
