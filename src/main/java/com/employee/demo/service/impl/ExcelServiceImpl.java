package com.employee.demo.service.impl;

import com.employee.demo.apiResponse.ApiResponse;
import com.employee.demo.service.ExcelService;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelServiceImpl  implements ExcelService {
    public final EmployeeRepository repository;

    public ExcelServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveExcelData(MultipartFile file) {
        try{
            List<Employee> employeeList=parseExcelFile(file.getInputStream());
            repository.saveAll(employeeList);
        }catch (Exception e){
            throw  new RuntimeException(ApiResponse.FAIL_STORE_FILE_DATA.getMessage());
        }
    }

    private List<Employee> parseExcelFile(InputStream inputStream) {
        List<Employee> employees=new ArrayList<>();
        try{
            Workbook workbook=new XSSFWorkbook(inputStream);
            Sheet sheet=workbook.getSheetAt(0);
            Iterator<Row> rows=sheet.iterator();

            boolean firstRow=true;
            while(rows.hasNext()){
                Row currRow=rows.next();

                if(firstRow){
                    firstRow=false;
                    continue;
                }
                Employee employee=new Employee();
                employee.setName(currRow.getCell(0).getStringCellValue());
//                employee.setDepartment(currRow.getCell(1).getStringCellValue());
                employee.setSalary(currRow.getCell(2).getNumericCellValue());
                employees.add(employee);

            }
            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException(ApiResponse.FAIL_PARSE_FILE_DATA.getMessage());
        }
        return employees;
    }
}
