package com.employee.demo.service.impl;

import com.employee.demo.apiResponse.ApiResponse;
import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.request.DepartmentRequestWithEmployeeList;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.request.EmployeeRequestList;
import com.employee.demo.response.DepartmentPageResponse;
import com.employee.demo.response.DepartmentResponse;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream().map(DepartmentResponse::new).toList();
    }

    @Override
    public ResponseEntity<?> getDepartmentById(Long id) {
        Department department=departmentRepository.findById(id).orElse(null);
        if(department==null) return ResponseEntity.badRequest().body(ApiResponse.DEPARTMENT_NOT_FOUND);
        return new ResponseEntity<>(new DepartmentResponse(department),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getDepartmentByName(String name) {
        List<DepartmentResponse> departments=departmentRepository.findByName(name).stream().map(DepartmentResponse::new).toList();
        if(departments.isEmpty())return ResponseEntity.badRequest().body(ApiResponse.DEPARTMENT_NOT_FOUND);
        return new ResponseEntity<>(departments,HttpStatus.OK);
    }

    @Override
    public Department saveDepartment(DepartmentRequest request) {
        Department department =new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setLocation(request.getLocation());
        department.setStatus(1);
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Map<String, Double> getTotalSalaryPerDepartment() {
        List<Department> departments= departmentRepository.findAll();
        return departments.stream().collect(Collectors.toMap(Department::getName,
                department ->department.getEmployeeList().stream().map(Employee::getSalary).reduce(0.0,Double::sum)));
    }

    @Override
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        List<Department> departments=departmentRepository.findAll();
        return departments.stream().collect(Collectors.toMap(Department::getName
                ,department -> department.getEmployeeList().stream().map(EmployeeResponse::new).toList()));
    }

    @Override
    public Set<String> getUniqueEmployeeDepartments() {
        return departmentRepository.findAll().stream().map(Department::getName).collect(Collectors.toSet());
    }

    @Override
    public Map<String, Long> countPerDepartment() {
        return departmentRepository.findAll().stream().collect(Collectors.toMap(Department::getName,
                department -> (long) department.getEmployeeList().size()));
    }

    @Override
    public String getDepartmentWithHighestSalary() {
//        Long id=departmentRepository.findAll().stream().collect(Collectors.toMap(Department::getId,
//                department -> department.getEmployeeList().stream().mapToDouble(Employee::getSalary).sum()))
//                .entrySet().stream().max(Map.Entry.comparingByValue())
//                .map(Map.Entry::getKey).get();
        String department=departmentRepository.findDepartmentWithHighestSalary();
        return department.isBlank()?"":department;
    }
    @Transactional
    @Override
    public ResponseEntity<?> addAndUpdateEmployeeListByDepartment(DepartmentRequestWithEmployeeList requests) {
        Department savedDepartment=new Department();
        List<Long> invalidEmployeeRequestList=new ArrayList<>();
        if(requests.getId()==0) {
            Department department2=departmentRepository.findByNameAndLocation(requests.getName(),requests.getLocation()).orElse(null);
            if(department2==null) {
                Department department = new Department();
                department.setName(requests.getName());
                department.setLocation(requests.getLocation());
                department.setDescription(requests.getDescription());
                department.setStatus(1);
                savedDepartment = departmentRepository.save(department);
            }else {
                return ResponseEntity.badRequest().body(ApiResponse.ID_ALREADY_EXIST_WITH_GIVEN_DATA);
            }
        }else{
            Department oldDepartment=departmentRepository.findById(requests.getId()).orElse(null);
            if(oldDepartment!=null) {
                if(requests.getName().isBlank() && requests.getDescription().isBlank() && requests.getLocation().isBlank()){
                    savedDepartment=oldDepartment;
                }else {
                    oldDepartment.setName(requests.getName().isBlank() ? oldDepartment.getName() : requests.getName());
                    oldDepartment.setLocation(requests.getLocation().isBlank() ? oldDepartment.getLocation() : requests.getLocation());
                    oldDepartment.setDescription(requests.getDescription().isBlank() ? oldDepartment.getDescription() : requests.getDescription());
                    savedDepartment = departmentRepository.save(oldDepartment);
                }
            }else{
                return ResponseEntity.badRequest().body(ApiResponse.INVALID_DEPARTMENT_ID);
            }
        }
        Department finalDepartment = savedDepartment;

        List<Employee> employeeList=requests.getEmployeeRequestList().stream().map(employeeRequest -> {

            if(employeeRequest.getId()==0 ) {
                Employee employee2=employeeRepository.findByEmail(employeeRequest.getEmail()).orElse(null);
                if(employee2==null) {
                    Employee employee = new Employee();
                    employee.setName(employeeRequest.getName());
                    employee.setDepartment(finalDepartment);
                    employee.setStatus(1);
                    employee.setEmail(employeeRequest.getEmail());
                    employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
                    employee.setSalary(employeeRequest.getSalary());
                    return employeeRepository.save(employee);
                }else{
                    throw new RuntimeException("the email id already exist Please try with another email");
                }
            }else {
                Employee oldEmployee=employeeRepository.findById(employeeRequest.getId()).orElse(null);
                if(oldEmployee!=null) {
                    oldEmployee.setName(employeeRequest.getName().isBlank() ? oldEmployee.getName() : employeeRequest.getName());
                    oldEmployee.setDepartment(finalDepartment);
                    oldEmployee.setSalary(employeeRequest.getSalary() == null ? oldEmployee.getSalary() : employeeRequest.getSalary());
                    return employeeRepository.save(oldEmployee);
                }else {
                    invalidEmployeeRequestList.add(employeeRequest.getId());
                }
            }
            return null;
        }).toList();
        if(!invalidEmployeeRequestList.isEmpty()){
            Object[] array=new Object[3];
            array[0]="the list of invalid requests ";
            array[1]=invalidEmployeeRequestList;
            array[2]=new DepartmentResponse(finalDepartment);
            return new ResponseEntity<>(array,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new DepartmentResponse(finalDepartment), HttpStatus.OK);
    }

    @Override
    public List<EmployeeResponse> getEmpEarnMoreThanAvgSalary() {
        return departmentRepository.findAll().stream().flatMap(department -> {
            double averageSalary = department.getEmployeeList().stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
           return department.getEmployeeList().stream().filter(employee -> employee.getSalary()>averageSalary);
        }).map(EmployeeResponse::new).toList();
    }
}
