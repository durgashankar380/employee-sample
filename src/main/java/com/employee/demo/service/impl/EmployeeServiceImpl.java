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
    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest)
    {
        Employee employee=new Employee();
        employee.setName(employeeRequest.getName());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setSalary(employeeRequest.getSalary());
        Employee saveedEmployee = employeeRepository.save(employee);
        return new EmployeeResponse(saveedEmployee.getEmployeeId(),saveedEmployee.getName(),saveedEmployee.getDepartment(),saveedEmployee.getSalary());
    }

    @Override
    public Map<String,Double> getTotalSalaryByDepartment() {

        List<Employee> employees = employeeRepository.findAll();
        Map<String, Double> map = new HashMap<>();
        for (Employee emp : employees) {
            String dep = emp.getDepartment();
            Double sal = emp.getSalary();
            map.put(dep, map.getOrDefault(dep, 0.0) + sal);
        }
        return map;
    }

    @Override
    public Map<String,List<EmployeeResponse>> getEmployeesGroupedByDepartment()
    {
        List<Employee> employees =employeeRepository.findAll();
        Map<String,List<EmployeeResponse>> groupedEmployee =new HashMap<>();
        for(Employee emp : employees)
        {
            groupedEmployee.putIfAbsent(emp.getDepartment(),new ArrayList<>());
            groupedEmployee.get(emp.getDepartment()).add(new EmployeeResponse(emp.getEmployeeId(),emp.getName(),emp.getDepartment(),emp.getSalary()));
        }
          return groupedEmployee;
    }

    @Override
    public Set<String> getUniqueEmployeeDepartments(){
        List<Employee> employees =employeeRepository.findAll();
        Set<String> uniqueDepartments =new HashSet<>();
        for(Employee emp : employees)
        {
            uniqueDepartments.add(emp.getDepartment());
        }
        return uniqueDepartments;
    }

    @Override
    public Map<Long,EmployeeResponse> getEmployeeById() {
        List<Employee> employee = employeeRepository.findAll();
        Map<Long,EmployeeResponse> employeeMap =new HashMap<>();
        for(Employee emp : employee)
        {
            employeeMap.put(emp.getEmployeeId(),new EmployeeResponse(emp.getEmployeeId(),emp.getName(),emp.getDepartment(),emp.getSalary()));
        }
        return  employeeMap;

    }

    @Override
    public List<EmployeeResponse> getEmployeesSortedBySalary() {
        List<Employee> employees=  employeeRepository.findAll();

        List<EmployeeResponse> responseList =new ArrayList<>();
        for (Employee employee :employees){
            EmployeeResponse employeeResponse =new EmployeeResponse(employee.getEmployeeId(),employee.getDepartment(),employee.getName(),employee.getSalary());
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
    public Map<String, Long> getcountperDepartment() {
        List<Employee> employees =employeeRepository.findAll();
        Map<String,Long> countMap =new HashMap<>();
        for(Employee emp : employees) {
            String department =emp.getDepartment();
            if(countMap.containsKey(department)){
                countMap.put(department,countMap.get(department)+1);
            }
            else{
                countMap.put(department,1L);
            }
        }
        return countMap;
    }
     @Override
      public Queue<EmployeeResponse> queueOfEmployee(){
        List<Employee> employees=employeeRepository.findAll();
        Queue<EmployeeResponse> queue =new LinkedList<>();
        for(Employee emp : employees)
        {
            queue.offer(new EmployeeResponse(emp.getEmployeeId(),emp.getName(),emp.getDepartment(),emp.getSalary()));
        }
        return queue;
     }






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
    public Stack<EmployeeResponse> stackOfEmployee() {
        List<Employee> employeeEntities=employeeRepository.findAll();
        Stack<EmployeeResponse> stack=new Stack<>();
        for (Employee emp : employeeEntities) {
            stack.push(new EmployeeResponse(emp.getEmployeeId(),emp.getName(),emp.getDepartment(),emp.getSalary()));
        }
        return stack;
    }

}
