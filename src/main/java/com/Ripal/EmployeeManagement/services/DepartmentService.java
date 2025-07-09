package com.Ripal.EmployeeManagement.services;

import com.Ripal.EmployeeManagement.dao.DepartmentDao;
import com.Ripal.EmployeeManagement.entities.Department;
import com.Ripal.EmployeeManagement.models.DepartmentRequestDto;
import com.Ripal.EmployeeManagement.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {
    private final DepartmentDao dao;

    public Department createDepartment(DepartmentRequestDto dto){
        dao.validateByName(dto.getName());
        Department dept = Department.builder().name(dto.getName()).isExist(true).build();
        return dao.saveAndReturn(dept);
    }

    public Page<Department> getAllDepartments(Integer page, Integer pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        return dao.findAllDepartment(pageable);
    }

    public Department updateDepartment(Integer id, DepartmentRequestDto dto){
        Department dept = dao.validateAndGetById(id);
        dept.setName(dto.getName());
        return dao.saveAndReturn(dept);
    }

    public void deleteDepartment(Integer id){
        Department dept= dao.validateAndGetById(id);
        dept.setExist(false);
        dao.saveAndReturn(dept);
    }

    public Department getDepartmentById(Integer id){
        return dao.validateAndGetById(id);
    }
}
