package com.employee.demo.controller3;

import com.employee.demo.model.Employee;
import com.employee.demo.service3.EmployeeService3;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class EmployeeController3 {

    @Autowired
   private EmployeeService3 service3;


    @PostMapping(value = "/upload")
    public ResponseEntity<String> saveFileData(@RequestParam("file")MultipartFile file) throws IOException, InvalidFormatException {
        if(!file.getOriginalFilename().endsWith("xlsx"))return ResponseEntity.badRequest().body("FILE FORMATE IS WRONG");
        service3.saveFileData(file);
    return   ResponseEntity.ok("Excel file Data saved into DataBase");
    }

    @GetMapping("/read-data")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(service3.findAll());
    }

}
