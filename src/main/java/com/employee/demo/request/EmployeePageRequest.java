package com.employee.demo.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePageRequest {
    private Integer pageSize;
    private Integer pageNumber;
    private String sortBy;
    private String sortDir;
    private String searchBy;
    private Integer status;
}
