package com.employee.demo.response;

import com.employee.demo.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeePageResponse {

    private List<EmployeeResponse> content;
    private int totalPage;
    private int size;
    private int numberOfElements;
    private int number;
    private long totalElements;
    private Sort sort;
    private Pageable pageable;
    private boolean first;
    private boolean last;
    private boolean empty;

    public EmployeePageResponse(Page<Employee> employees) {
        this.content = employees.getContent().stream().map(EmployeeResponse::new).toList();
        this.totalPage = employees.getTotalPages();
        this.size = employees.getSize();
        this.numberOfElements = employees.getNumberOfElements();
        this.number = employees.getNumber();
        this.totalElements = employees.getTotalElements();
        this.sort = employees.getSort();
        this.pageable = employees.getPageable();
        this.first = employees.isFirst();
        this.last = employees.isLast();
        this.empty = employees.isEmpty();
    }

}
