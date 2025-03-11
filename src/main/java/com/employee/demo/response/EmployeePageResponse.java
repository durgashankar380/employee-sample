package com.employee.demo.response;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeePageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePageResponse {
    private List<EmployeeResponse> content;
    private int totalPages;
    private int number;
    private Pageable pageable;
    private int size;
    private int numberOfElement;
    private long totalElement;
    private Sort sort;
    private boolean first;
    private boolean last;
    private boolean empty;

    public EmployeePageResponse (Page<Employee> employees){
        this.content=employees.getContent().stream().map(EmployeeResponse::new).toList();
        this.totalPages=employees.getTotalPages();
        this.number=employees.getNumber();
        this.pageable=employees.getPageable();
        this.size=employees.getSize();
        this.numberOfElement=employees.getNumberOfElements();
        this.totalElement=employees.getTotalElements();
        this.sort=employees.getSort();
        this.last=employees.isLast();
        this.first=employees.isFirst();
        this.empty=employees.isEmpty();
    }

}
