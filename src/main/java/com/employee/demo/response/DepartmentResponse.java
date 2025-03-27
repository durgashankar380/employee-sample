package com.employee.demo.response;

import com.employee.demo.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
    private Long id;
    private String name;
    private String location;
    private String description;
    private Integer status;
    private List<EmployeeResponse> responseList=new ArrayList<>();

    public DepartmentResponse(Department department){
        this.id=department.getId();
        this.name=department.getName();
        this.location=department.getLocation();
        this.description=department.getDescription();
        this.status=department.getStatus();
        this.responseList=department.getEmployeeList().stream().map(EmployeeResponse::new).toList();
    }
}
