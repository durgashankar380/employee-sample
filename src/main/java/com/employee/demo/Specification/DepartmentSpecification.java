package com.employee.demo.Specification;

import com.employee.demo.model.Department;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class DepartmentSpecification {

    public static Specification<Department> filterByCriteria(String keyword, Integer status) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Search by keyword (name, location, description)
            if (keyword != null && !keyword.isEmpty()) {
                String searchPattern = "%" + keyword.toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("name")), searchPattern),
                        cb.like(cb.lower(root.get("location")), searchPattern),
                        cb.like(cb.lower(root.get("description")), searchPattern)
                ));
            }

            // Status filtering logic
            if (status != null) {
                if (status == 0) {
                    predicates.add(root.get("status").in(1, 2)); // Include only status 1 and 2, exclude 3
                } else {
                    predicates.add(cb.equal(root.get("status"), status)); // Match exact status
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
