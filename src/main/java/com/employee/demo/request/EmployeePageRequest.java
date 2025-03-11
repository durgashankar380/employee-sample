package com.employee.demo.request;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePageRequest {
    private int page;
    private int size;
    private String sortBy;
    private String sortDirection;
    private String keyword;
    private int status;
}


