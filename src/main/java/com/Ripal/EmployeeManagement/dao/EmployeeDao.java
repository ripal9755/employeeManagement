package com.Ripal.EmployeeManagement.dao;

import com.Ripal.EmployeeManagement.entities.Employee;
import com.Ripal.EmployeeManagement.exceptions.NotFoundException;
import com.Ripal.EmployeeManagement.models.EmployeeRequestDto;
import com.Ripal.EmployeeManagement.models.EmployeeResponse;
import com.Ripal.EmployeeManagement.models.EmployementType;
import com.Ripal.EmployeeManagement.repositories.EmployeRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeDao {
    private final EmployeRespository employeeRepository;

    public Employee saveAndGet(Employee employee){
        Employee employee1 = employeeRepository.save(employee);
        return employee1;
    }

    public Employee validateAndGetEmployee(Long id){
        Optional<Employee> opt = employeeRepository.findByIdAndIsDeletedFalse(id);
        if(opt.isEmpty()){
            throw new NotFoundException("Employee with the given id is not exist");
        }
        return opt.get();
    }

    public Page<Employee> getAllEmployee(String name, EmployementType employeementType, Pageable pageable) {
        return employeeRepository.findByNameContainingAndEmployeementType(name, employeementType, pageable);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public Page<EmployeeResponse> findAllEmployees(Pageable pageable){
        return employeeRepository.findAllEmployees(pageable);
    }
}
