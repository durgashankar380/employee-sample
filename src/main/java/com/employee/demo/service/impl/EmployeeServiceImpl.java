package com.employee.demo.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.EmployeePagginationReq;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeService;
import com.lms.exception.APIStatus;
import com.lms.exception.CountryNotFoundException;
import com.lms.model.Country;
import com.lms.response.APIResponse;
import com.lms.util.IConstant;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

   
	

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setSalary(employeeRequest.getSalary());
        Employee savedEmployee = repository.save(employee);
        return new EmployeeResponse(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getDepartment(), savedEmployee.getSalary());
    }
    
    @Override
    public Map<String, Double> getTotalSalaryPerDepartment() {
        List<Employee> employees = repository.findAll();
        Map<String, Double> totalSalaryMap = new HashMap<>();
        for (Employee emp : employees) {
            totalSalaryMap.put(emp.getDepartment(), totalSalaryMap.getOrDefault(emp.getDepartment(), 0.0) + emp.getSalary());
        }
        return totalSalaryMap;
    }

    @Override
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        List<Employee> employees = repository.findAll();
        Map<String, List<EmployeeResponse>> groupedEmployees = new HashMap<>();
        for (Employee emp : employees) {
            groupedEmployees.putIfAbsent(emp.getDepartment(), new ArrayList<>());
            groupedEmployees.get(emp.getDepartment()).add(new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        }
        return groupedEmployees;
    }

    @Override
    public Set<String> getUniqueEmployeeDepartments() {
        List<Employee> employees = repository.findAll();
        Set<String> uniqueDepartments = new HashSet<>();
        for (Employee emp : employees) {
            uniqueDepartments.add(emp.getDepartment());
        }
        return uniqueDepartments;
    }

    @Override
    public Map<Long, EmployeeResponse> getEmployeesByIdMap() {
        List<Employee> employees = repository.findAll();
        Map<Long, EmployeeResponse> employeeMap = new HashMap<>();
        for (Employee emp : employees) {
            employeeMap.put(emp.getId(), new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        }
        return employeeMap;
    }




	@Override
	public void processAndSaveEmployees(MultipartFile file) {
		 List<Employee> employees = new ArrayList<>();
	        Workbook workbook = null;
			try {
				workbook = new XSSFWorkbook(file.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
	        Sheet sheet = workbook.getSheetAt(0);
	        Iterator<Row> rowIterator = sheet.iterator();

	        // Skip header row
	        if (rowIterator.hasNext()) {
	            rowIterator.next();
	        }

	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            Employee employee = new Employee();

	            employee.setName(row.getCell(0).getStringCellValue());
	            employee.setDepartment(row.getCell(1).getStringCellValue());
	            employee.setSalary(row.getCell(2).getNumericCellValue());

	            employees.add(employee);
	        }
	        try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        // Save all employees to the database
	        repository.saveAll(employees);
		
	}
	
	 @Override
	    public List<Employee> getTop3HighestPaidEmployeesInEachDepartment() {
	        return repository.findTop3HighestPaidEmployeesInEachDepartment();
	    }

	    @Override
	    public List<Employee> getEmployeesWithSecondHighestSalary() {
	        return repository.findEmployeesWithSecondHighestSalary();
	    }

	    @Override
	    public String getDepartmentWithHighestTotalSalary() {
	        return repository.findDepartmentWithHighestTotalSalary();
	    }

	    @Override
	    public List<Employee> getEmployeesEarningMoreThanDepartmentAverage() {
	        return repository.findEmployeesEarningMoreThanDepartmentAverage();
	    }

	    @Override
	    public String getMostCommonFirstLetterInEmployeeNames() {
	        return repository.findMostCommonFirstLetter();
	    }




		@Override
		public Object getAllEmployee(EmployeePagginationReq employeePagginationReq) {

	        try {
	            if (employeePagginationReq.getPageSize() > 0) {
	                Pageable pageable = createPageRequest(employeePagginationReq);
	                Page<Employee> page = fetchEmployee(employeePagginationReq, pageable);
	            } else {
	                List<Employee> countryList = fetchEmployee(employeePagginationReq, createSort(employeePagginationReq));
	            }
	        } catch (Exception ex) {


	        }
		}
}