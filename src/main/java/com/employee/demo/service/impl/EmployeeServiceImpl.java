package com.employee.demo.service.impl;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;


    @Override
    public List<Employee> stackOfEmployee() {
        List<Employee> employeeEntities=employeeRepository.findAll();
        Stack<Employee> stack=new Stack<>();
        List<Employee> list  =new ArrayList<>();
        for (Employee employee : employeeEntities) {
            stack.push(employee);
        }
        do {
            list.add(stack.peek());
            stack.pop();
        }while(!stack.isEmpty());
        return list;
    }



//    @Override
//    public Queue<EmployeeResponse> getEmployeesAsQueue() {
//        List<Employee> employees = repository.findAll();
//        Queue<EmployeeResponse> queue = new LinkedList<>();
//
//        for (Employee emp : employees) {
//            queue.offer(new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
//        }
//        return queue;
//    }


    @Override
    public List<String> getAllEmployeeName()
    {
        List<Employee> employeeEntities =employeeRepository.findAll();
        List<String> empName =new ArrayList<>();
        for(Employee employee : employeeEntities)
        {
            empName.add(employee.getName());
        }
        return empName;
    }


    @Override
    public ResponseEntity<Employee>addMultipleEmployees(@RequestBody List<Employee> employeeEntity)
    {
        try{
            if(employeeEntity == null || employeeEntity.equals(""))
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            for(Employee employee: employeeEntity)
            {
                boolean b= employee.getEmployeeId()!=0 && employee.getSalary()!=0 && employee.getDepartment()!=null && employee.getName()!=null;
                if(b)
                {
                    employeeRepository.save(employee);

                }
            }
            // return null;
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @Override
    public Map<String,Double> getTotalSalaryByDepartment() {

        List<Employee> employees = employeeRepository.findAll();
        Map<String, Double> map = new HashMap<>();
        for (Employee employeeEntity : employees) {

            String dep = employeeEntity.getDepartment();
            Double sal = employeeEntity.getSalary();
            map.put(dep, map.getOrDefault(dep, 0.0) + sal);
        }


        return map;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees()
    {
        List<Employee> employeeEntity=  employeeRepository.findAll();

        List<EmployeeResponse> responseList =new ArrayList<>();
        for (Employee employee :employeeEntity){
            EmployeeResponse employeeResponse =new EmployeeResponse();
            BeanUtils.copyProperties(employee,employeeResponse);
            responseList.add(employeeResponse);
        }

        Collections.sort(responseList, new Comparator<EmployeeResponse>() {
            @Override
            public int compare(EmployeeResponse e1, EmployeeResponse e2) {
                return Double.compare(e2.getSalary(),e1.getSalary());
            }
        });
        return responseList;

    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        Optional<Employee> byemployeeOptional = employeeRepository.findById(id);
        if(byemployeeOptional.isPresent()){
            return  byemployeeOptional;

        }else {
            System.out.println("id not null");
        }
        return  null;

    }

    @Override
    public Employee updateEmpById(long id, Employee employeeEntity) {
        Employee employee=employeeRepository.findById(id).orElse(null);
        if(employee!=null)
        {
            employee.setName(employeeEntity.getName()!=null && !employeeEntity.getName().equals("") ? employeeEntity.getName() : employee.getName());
            employee.setDepartment(employeeEntity.getDepartment()!=null && !employeeEntity.getDepartment().equals("")? employeeEntity.getDepartment() : employee.getDepartment());
            employee.setSalary(employeeEntity.getSalary() );
        }
        return employeeRepository.save(employee);

    }

    @Override
    public Employee getEmployeeByName(String name) {


        return employeeRepository.findByName(name);
    }

    @Override
    public List<Employee> getAllEmployeeByDepartment(String department)
    {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public ResponseEntity<?> totalSalaryByDepartment(String dept) {
        List<Employee> employeeEntityList=employeeRepository.findAll();
        Map<String ,Double> map=new HashMap<>();

        for( Employee entity:employeeEntityList){
            String department=entity.getDepartment();
            Double salary=entity.getSalary();
            map.put(department,map.getOrDefault(department,0.0)+salary);
        }

        if(map.containsKey(dept))return new ResponseEntity<>(map.get(dept), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public Map<String, Long> getcountperDepartment() {
        return Map.of();
    }

//    @Override
//    public Map<String, Long> getcountperDepartment() {
//        Map<String,Long> departmentCount=new HashMap<>();
//        List<Employee> employeeEntities = employeeRepository.findAll();
//        Set<String> distinctDepartment =new HashSet<>();
//        for(Employee employee :employeeEntities)
//        {
//            distinctDepartment.add(employee.getDepartment());
//        }
//        for(String department : distinctDepartment)
//        {
//            long count=employeeRepository.findByDepartment(department).size();
//            departmentCount.put(department,count);
//        }
//
//
//        return departmentCount;
//    }

//    @Override
//    public ResponseEntity<Employee> addMultipleEmployees(List<Employee> employeeEntity) {
//        return null;
//    }


    @Override
    public Employee addEmployee(EmployeeRequest employee)
    {
        Employee employees=new Employee();
        employees.setName(employee.getName());
        employees.setDepartment(employee.getDepartment());
        Employee emp= employeeRepository.save(employees);
        return null;
    }
}
