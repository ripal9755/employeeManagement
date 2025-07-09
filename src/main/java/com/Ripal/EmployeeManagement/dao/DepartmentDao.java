package com.Ripal.EmployeeManagement.dao;

import com.Ripal.EmployeeManagement.entities.Department;
import com.Ripal.EmployeeManagement.exceptions.AlreadyExistException;
import com.Ripal.EmployeeManagement.exceptions.NotFoundException;
import com.Ripal.EmployeeManagement.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentDao {
    private final DepartmentRepository repo;

    public Department validateAndGetById(Integer id) {
        Optional<Department> opt = repo.findByIdAndIsExistTrue(id);
        if (opt.isEmpty()) {
            throw new NotFoundException("There is no Department with the given id: " + id);
        }
        return opt.get();
    }

    public void validateByName(String name) {
        Optional<Department> opt = repo.findByNameAndIsExistTrue(name);
        if (opt.isPresent()) {
            throw new AlreadyExistException("Department name is already exist");
        }
    }

    public Page<Department> findAllDepartment(Pageable pageable) {
        return repo.findByIsExistTrue(pageable);
    }

    public Department saveAndReturn(Department dept) {
        return repo.save(dept);
    }
}
