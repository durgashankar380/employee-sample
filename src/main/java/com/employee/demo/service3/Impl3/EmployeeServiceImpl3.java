package com.employee.demo.service3.Impl3;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.service3.EmployeeService3;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeServiceImpl3 implements EmployeeService3 {

    @Autowired
    private EmployeeRepository repository;


    @Override
    public void saveFileData(MultipartFile file) throws IOException {
        List<Employee> employeeList=new LinkedList<>();
        Workbook workbook= new XSSFWorkbook(file.getInputStream());
        Sheet sheet= workbook.getSheetAt(0);
        sheet.forEach(row->{
            Employee emp =new Employee();
            if(row.getRowNum()!=0){
                emp.setDepartment(row.getCell(1).getStringCellValue());
                emp.setName(row.getCell(2).getStringCellValue());
                emp.setSalary(row.getCell(3).getNumericCellValue());
                employeeList.add(emp);
            }
        });
        repository.saveAll(employeeList);
    }

    public List<Employee> findAll(){
        return repository.findAll();
}





}
