package com.employee.demo.response;

import com.employee.demo.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeResponse {

    private long employeeId;
    private String name;
    private String department;
    private double Salary;
    private Integer status;
    private String email;

    public EmployeeResponse(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.name = employee.getName();
        this.department = employee.getDepartment();
        this.Salary = employee.getSalary();
        this.status = employee.getStatus();
        this.email = employee.getEmail();
    }


}
