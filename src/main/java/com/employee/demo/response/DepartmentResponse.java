package com.employee.demo.response;

import com.employee.demo.model.Department;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Data
public class DepartmentResponse {

    private Long id;
    private String name;
    private String location;
    private String description;
    private Integer status;
    private List<EmployeeResponse> employees;

    public DepartmentResponse(Long id, String name, String location, String description, List<EmployeeResponse> employees,Integer status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.employees = employees;
        this.status=status;
    }

    public DepartmentResponse(Department department, List<EmployeeResponse> employeeResponses) {

        this.id = department.getId();
        this.name = department.getName();
        this.location = department.getLocation();
        this.description = department.getDescription();
        this.employees = employeeResponses;
        this.status = department.getStatus();
    }


    public DepartmentResponse(Department finalDepartment) {
        this.id = finalDepartment.getId();
        this.name = finalDepartment.getName();
        this.location = finalDepartment.getLocation();
        this.description = finalDepartment.getDescription();
        this.employees = finalDepartment.getEmployee().stream().map(EmployeeResponse::new).toList();
        this.status = finalDepartment.getStatus();
    }
}
