package com.employee.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    void saveExcelData(MultipartFile file);
}
