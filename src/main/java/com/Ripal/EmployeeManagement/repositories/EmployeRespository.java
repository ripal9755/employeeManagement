package com.Ripal.EmployeeManagement.repositories;

import com.Ripal.EmployeeManagement.entities.Employee;
import com.Ripal.EmployeeManagement.models.EmployeeResponse;
import com.Ripal.EmployeeManagement.models.EmployementType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeRespository extends JpaRepository<Employee, Long> {
    Page findByIsDeletedFalse(Pageable pageable);

    Optional<Employee> findByIdAndIsDeletedFalse(Long id);
    // containing got from google, haven't discussed in class
    Page<Employee> findByNameContainingAndEmployeementType(String name, EmployementType employeementType, Pageable pageable);

    @Query(value = """
                select new com.Ripal.EmployeeManagement.models.EmployeeResponse(e.id, e.name,e.employeementType, d.name, e.salary)
                from Employee e inner join Department d
                ON
                 d = e.department
          """, countQuery = "select count(*) from Employee")
    Page<EmployeeResponse> findAllEmployees(Pageable pageable);

}
