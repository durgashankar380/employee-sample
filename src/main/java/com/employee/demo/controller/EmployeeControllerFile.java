package com.employee.demo.controller;

import com.employee.demo.apiStatus.APIStatus;
import com.employee.demo.model.Employee;
import com.employee.demo.service.EmployeePageService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class EmployeeControllerFile {

    @Autowired
    private EmployeePageService service3;


    @PostMapping(value = "/upload")
    public ResponseEntity<String> saveFileData(@RequestParam("file") MultipartFile file) throws IOException, InvalidFormatException {
        if (!file.getOriginalFilename().endsWith("xlsx"))
            return ResponseEntity.badRequest().body(APIStatus.EMPLOYEE_INVALID_FILE_FORMAT.getMessage());
        service3.saveFileData(file);
        return ResponseEntity.ok(APIStatus.EMPLOYEE_FILE_DATA_SAVE.getMessage());
    }

    @GetMapping("/read-data")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(service3.findAll());
    }

}
