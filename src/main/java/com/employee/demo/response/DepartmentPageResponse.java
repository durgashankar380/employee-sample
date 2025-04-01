package com.employee.demo.response;

import com.employee.demo.model.Department;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Data
public class DepartmentPageResponse {

    private List<DepartmentResponse> content;

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

    public DepartmentPageResponse (Page<Department> departmentPage){

        this.content=departmentPage.getContent().stream().map(DepartmentResponse::new).toList();

        this.totalPages=departmentPage.getTotalPages();

        this.number=departmentPage.getNumber();

        this.pageable=departmentPage.getPageable();

        this.size=departmentPage.getSize();

        this.numberOfElement=departmentPage.getNumberOfElements();

        this.totalElement=departmentPage.getTotalElements();

        this.sort=departmentPage.getSort();

        this.last=departmentPage.isLast();

        this.first=departmentPage.isFirst();

        this.empty=departmentPage.isEmpty();

    }
}
