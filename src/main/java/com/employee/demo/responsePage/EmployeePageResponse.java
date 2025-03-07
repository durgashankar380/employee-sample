package com.employee.demo.responsePage;

import com.employee.demo.response.EmployeeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

}
