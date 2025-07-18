package com.Ripal.EmployeeManagement.services;

import com.Ripal.EmployeeManagement.dao.DepartmentDao;
import com.Ripal.EmployeeManagement.dao.EmployeeDao;
import com.Ripal.EmployeeManagement.entities.Department;
import com.Ripal.EmployeeManagement.entities.Employee;
import com.Ripal.EmployeeManagement.models.EmployeeRequestDto;
import com.Ripal.EmployeeManagement.models.EmployeeResponse;
import com.Ripal.EmployeeManagement.models.EmployementType;
import com.Ripal.EmployeeManagement.repositories.EmployeRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;
    private final EmployeRespository employeeRepo;
    public Employee createEmployee(EmployeeRequestDto dto){
        Department department = departmentDao.validateAndGetByName(dto.getDepartmentName());
        Employee employee = Employee.builder()
                .name(dto.getName())
                .employeementType(dto.getEmployementType())
                .department(department)
                .salary(dto.getSalary())
                .build();
        return employeeDao.saveAndGet(employee);
    }

    public Page<Employee> getAllEmployee(String name, EmployementType employeementType, Integer page, Integer pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        //String type = employeementType.toString();
        return employeeDao.getAllEmployee(name,employeementType, pageable);
    }

    public Employee updateEmployee(Long id, EmployeeRequestDto employeeRequestDto){
        Employee oldEmployee = employeeDao.validateAndGetEmployee(id);
        Department department = departmentDao.validateAndGetByName(employeeRequestDto.getDepartmentName());
        oldEmployee.setName(employeeRequestDto.getName());
        oldEmployee.setSalary(employeeRequestDto.getSalary());
        oldEmployee.setDepartment(department);
        oldEmployee.setEmployeementType(employeeRequestDto.getEmployementType());
        return employeeDao.saveAndGet(oldEmployee);
    }

    public void deleteEmployee(Long id){
        employeeDao.deleteEmployee(id);
    }

    public Page<EmployeeResponse> findAllEmployees(Pageable pageable){
       return  employeeDao.findAllEmployees(pageable);
    }
}
