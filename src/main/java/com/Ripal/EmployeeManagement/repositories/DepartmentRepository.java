package com.Ripal.EmployeeManagement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Ripal.EmployeeManagement.entities.Department;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Page<Department> findByIsExistTrue(Pageable pageable);

    Optional<Department> findByIdAndIsExistTrue(Integer id);

    Optional<Department> findByNameAndIsExistTrue(String name);
}
