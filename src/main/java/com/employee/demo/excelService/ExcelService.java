package com.employee.demo.excelService;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    void saveExcelData(MultipartFile file);
}
