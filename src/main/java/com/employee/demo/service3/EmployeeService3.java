package com.employee.demo.service3;

import com.employee.demo.model.Employee;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService3 {
    void saveFileData(MultipartFile file) throws IOException, InvalidFormatException;

    List<Employee> findAll();
}
