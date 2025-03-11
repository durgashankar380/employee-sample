package com.employee.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeStatusRepository;
import com.employee.demo.request.EmployeeStatusRequest;
import com.employee.demo.response.EmployeeStatusResponse;
import com.employee.demo.service.EmployeeStatusService;

@Service
public class EmployeeStatusServiceImpl implements EmployeeStatusService {

    @Autowired
    private EmployeeStatusRepository employeeStatusRepository;
    

    @Override
    public EmployeeStatusResponse manageEmployeeStatus(EmployeeStatusRequest request) {
        Optional<Employee> employeeOptional = employeeStatusRepository.findById(request.getId());

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            String message;
            switch (request.getStatus()) {
                case 1:
                    message = "Data activated successfully";
                    employee.setStatus(1);
                    break;
                case 2:
                    message = "Data inactivated successfully";
                    employee.setStatus(2);
                    break;
                case 3:
                    message = "Data deleted successfully subDelete";
                    employee.setStatus(3);
                    break;
                default:
                    return new EmployeeStatusResponse("Invalid status");
            }
            
            employeeStatusRepository.save(employee);

            return new EmployeeStatusResponse(message);
        } else {
            return new EmployeeStatusResponse(
                "Employee not found" 
            );
        }
    }
    
    
    @Override
    public EmployeeStatusResponse addOrUpdateEmployeeStatus(EmployeeStatusRequest request) {
        if (request.getId() == 0) {
            // Create a new employee
            Employee newEmployee = new Employee();
            newEmployee.setName(request.getName());
            newEmployee.setDepartment(request.getDepartment());
            newEmployee.setSalary(request.getSalary());
            newEmployee.setStatus(request.getStatus());

            employeeStatusRepository.save(newEmployee);
            return new EmployeeStatusResponse("New Employee added successfully");
        } else {
            // Search for the existing employee
            Optional<Employee> existingEmployee = employeeStatusRepository.findById(request.getId());
            if (existingEmployee.isPresent()) {
                Employee emp = existingEmployee.get();
                emp.setStatus(request.getStatus()); 
                employeeStatusRepository.save(emp);
                return new EmployeeStatusResponse("Employee status updated");
            } else {
                return new EmployeeStatusResponse("Employee does not exist");
            }
        }
    }
}
