package com.employee.demo.service.impl;

import com.employee.demo.apiResponse.ApiResponse;
import com.employee.demo.model.Department;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.DepartmentPageResponse;
import com.employee.demo.service.DepartmentPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentPageServiceImpl implements DepartmentPageService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<?> getPage(EmployeePageRequest request) {
        Pageable pageable;
        Page<Department> departmentPage = null;
        int size=departmentRepository.findAll().size();
        String sortBy="id";
        Sort.Direction sort = request.getSortDir().equalsIgnoreCase("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
        if(request.getPageSize()>0){
            size=request.getPageSize();
        }
        if(!request.getSortBy().isBlank()){
            sortBy=request.getSortBy();
        }


        pageable=PageRequest.of(request.getPageNumber(),
                size,
                Sort.by(sort,sortBy));


        if(!request.getSearchBy().isBlank()){
            if(request.getStatus()!=null) {
                if(request.getStatus()>0 && request.getStatus()<4)
                    departmentPage = departmentRepository.search(pageable,request.getSearchBy(),request.getStatus());
                else if (request.getStatus()==0)
                    departmentPage = departmentRepository.searchNotStatus(pageable,request.getSearchBy(),3);
            } else {
                departmentPage = departmentRepository.searchNotStatus(pageable,request.getSearchBy(),3);
            }
        }
        else {
            if(request.getStatus()!=null) {
                if(request.getStatus()>0 && request.getStatus()<4)
                    departmentPage = departmentRepository.findByStatus(pageable,request.getStatus());
                else if (request.getStatus()==0)
                    departmentPage = departmentRepository.findByStatusNot(pageable,3);
            } else {
                departmentPage = departmentRepository.findByStatusNot(pageable,3);
            }
        }

        assert departmentPage != null;
        return new ResponseEntity<>(new DepartmentPageResponse(departmentPage), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> manageStatus(Long id, Integer status) {
        Department oldDepartment=departmentRepository.findById(id).orElse(null);
        if(oldDepartment==null){
            return ResponseEntity.notFound().build();
        }else if(status>0 && status<4){
            oldDepartment.setStatus(status);
            departmentRepository.save(oldDepartment);
            if(status==1)return ResponseEntity.ok(ApiResponse.DEPARTMENT_STATUS_ACTIVATED_SUCCESSFULLY);
            if(status==2)return ResponseEntity.ok(ApiResponse.DEPARTMENT_STATUS_DEACTIVATED_SUCCESSFULLY);
            return ResponseEntity.ok(ApiResponse.DEPARTMENT_STATUS_TEMPORARILY_DELETED_SUCCESSFULLY);
        }
        return  ResponseEntity.badRequest().body(ApiResponse.INVALID_STATUS);
    }
}
