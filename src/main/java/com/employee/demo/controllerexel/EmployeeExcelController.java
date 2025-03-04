package com.employee.demo.controllerexel;

import com.employee.demo.excelService.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/employeeExcel")
public class EmployeeExcelController {

    @Autowired
    private ExcelService service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file")MultipartFile file){
        if(!file.getOriginalFilename().endsWith(".xlsx")){
            return ResponseEntity.badRequest().body("Only Excel files (.xlsx) are supported ");
        }
        service.saveExcelData(file);
        return ResponseEntity.ok("file uploaded and data inserted successfully");
    }

//    @GetMapping("/download")
//    public ResponseEntity<byte[]> downloadExcel(){
//        try{
//            byte[] excel=service.generateExcel();
//            HttpHeaders headers=new HttpHeaders();
//            headers.add("Content-Disposition","attachment;filename=employee.xlsx");
//            headers.add("")
//        }
//    }
}
