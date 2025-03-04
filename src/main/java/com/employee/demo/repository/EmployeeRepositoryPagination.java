package com.employee.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.demo.model.Employee;

public interface EmployeeRepositoryPagination extends JpaRepository<Employee, Long> {

}
