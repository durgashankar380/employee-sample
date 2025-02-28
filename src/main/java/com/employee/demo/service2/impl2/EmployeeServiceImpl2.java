package com.employee.demo.service2.impl2;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service2.EmployeeService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl2 implements EmployeeService2 {

    @Autowired
    private EmployeeRepository repository;


    @Override
    public void addEmployee(EmployeeRequest employeeRequest) {
        repository.insertEmployee(employeeRequest.getEmployeeId(), employeeRequest.getName(), employeeRequest.getDepartment(), employeeRequest.getSalary());
    }

    @Override
    public List<Employee> getAllEmployee() {
        return repository.getAllEmployee();
    }

    @Override
    public Set<String> getUniqueEmployeeDepartments() {
        return repository.getUniqueEmployeeDepartments();
    }

    @Override
    public List<Employee> queueOfEmployee() {
        return repository.QueueOfEmp();
    }

    @Override
    public List<Employee> stackOfEmployee() {
        return repository.StackOfEmp();
    }

    @Override
    public List<Employee> getEmployeesSortedBySalary() {
        return repository.sortedBySalary();
    }

    @Override
    public List<Employee> getTopThree() {
        return repository.findTop3HighestPaidEmployeesInEachDepartment();
    }

    @Override
    public List<Object> getTotalSalaryByDepartment() {
        return repository.getTotalSalaryByDepartment();
    }

    @Override
    public String findDepartmentWithHighestTotalSalary() {
        return repository.findDepartmentWithHighestTotalSalary();
    }

    @Override
    public List<Employee> findEmployeesAboveDepartmentAverageSalary() {
        return repository.findEmployeesAboveDepartmentAverageSalary();
    }

    @Override
    public List<Object[]> findMostCommonFirstLetter() {
        return repository.findMostCommonFirstLetter();
    }

    @Override
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        List<Object[]> results = repository.findAllEmployeesForGrouping();
        Map<String, List<EmployeeResponse>> departmentMap = new HashMap<>();

        for (Object[] result : results) {
            Long id = (Long) result[0];
            String name = (String) result[1];
            String department = (String) result[2];
            Double salary = (Double) result[3];

            EmployeeResponse response = new EmployeeResponse(id, name, department, salary);

            if (!departmentMap.containsKey(department)) {
                departmentMap.put(department, new ArrayList<>());
            }
            departmentMap.get(department).add(response);
        }
        return departmentMap;
    }

    @Override
    public List<String> getAllEmployeeName() {
        return repository.getAllNames();
    }

    @Override
    public List<Employee> findEmployeesWithSecondHighestSalary() {
        return repository.findEmployeesWithSecondHighestSalary();
    }

    @Override
    public Map<Long, EmployeeResponse> getEmployeeById(Long id) {
        List<Object[]> results = repository.findEmployeeByIdForMap(id);
        Map<Long, EmployeeResponse> employeeMap = new HashMap<>();

        if (!results.isEmpty()) {
            Object[] result = results.get(0);
            Long employeeId = (Long) result[0];
            String name = (String) result[1];
            String department = (String) result[2];
            Double salary = (Double) result[3];

            EmployeeResponse response = new EmployeeResponse(employeeId, name, department, salary);
            employeeMap.put(employeeId, response);
        }

        return employeeMap;
    }

    @Override
    public Map<String, Long> getcountperDepartment() {
        List<Object[]> results = repository.getCountPerDepartmentNative();
        Map<String, Long> departmentCountMap = new HashMap<>();

        for (Object[] result : results) {
            String department = (String) result[0];
            Long count = ((Number) result[1]).longValue(); // Safe conversion

            departmentCountMap.put(department, count);
        }
        return departmentCountMap;
    }

    @Override
    public Employee updateEmpById(long id, Employee employeeEntity) {
        int rowsAffected = repository.updateEmployeeByIdNative(id, employeeEntity.getName(), employeeEntity.getDepartment(), employeeEntity.getSalary());

        if (rowsAffected > 0) {
            Optional<Employee> updatedEmployee = repository.findById(id);
            return updatedEmployee.orElse(null); // Return null if not found
        } else {
            return null;
        }
    }

    public Optional<Employee> getEmployeeById(long id){
        return repository.findById(id);
    }


}
