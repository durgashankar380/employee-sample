package com.employee.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.demo.ApiStatus.ApiStatus;
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
    public EmployeeStatusResponse manageEmployeeStatus(Long id,int status) {
        Optional<Employee> employeeOptional = employeeStatusRepository.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            ApiStatus message;
            switch (status) {
                case 1:
                    message = ApiStatus.DATA_ACTIVATED_SUCCESSFULLY;
                    employee.setStatus(1);
                    break;
                case 2:
                    message = ApiStatus.DATA_INACTIVATED_SUCCESSFULLY;
                    employee.setStatus(2);
                    break;
                case 3:
                    message = ApiStatus.DATA_DELETED_SUCCESSFULLY;
                    employee.setStatus(3);
                    break;
                default:
                    return new EmployeeStatusResponse(ApiStatus.INVALID_STATUS);
            }
            
            employeeStatusRepository.save(employee);

            return new EmployeeStatusResponse(message);
        } else {
            return new EmployeeStatusResponse(ApiStatus.EMPLOYEE_NOT_FOUND);
        }
    }
    
    
    @Override
    public EmployeeStatusResponse addOrUpdateEmployeeStatus(EmployeeStatusRequest request) {
        if (request.getId() == 0) {
            // Create a new employee
            Employee newEmployee = new Employee();
            newEmployee.setName(request.getName());
         //   newEmployee.setDepartment(request.getDepartment());
            newEmployee.setSalary(request.getSalary());
            newEmployee.setStatus(request.getStatus());

            employeeStatusRepository.save(newEmployee);
            return new EmployeeStatusResponse(ApiStatus.NEW_EMPLOYEE_ADDED_SUCCESSFULLY);
        } else {
            // Search for the existing employee
            Optional<Employee> existingEmployee = employeeStatusRepository.findById(request.getId());
            if (existingEmployee.isPresent()) {
                Employee emp = existingEmployee.get(); 
                emp.setStatus(request.getStatus()); 
                employeeStatusRepository.save(emp);
                return new EmployeeStatusResponse(ApiStatus.EMPLOYEE_STATUS_UPDATED);
            } else {
                return new EmployeeStatusResponse(ApiStatus.EMPLOYEE_NOT_FOUND);
            }
        }
    }
    
 
    
}
