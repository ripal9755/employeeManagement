package com.Ripal.EmployeeManagement.controllers;

import com.Ripal.EmployeeManagement.entities.Department;
import com.Ripal.EmployeeManagement.models.DepartmentRequestDto;
import com.Ripal.EmployeeManagement.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Object> createDepartment(@RequestBody DepartmentRequestDto dto){
        return ResponseEntity.ok(departmentService.createDepartment(dto));
    }

    @GetMapping
    public Page<Department> getAllDepartments(@RequestParam int page, @RequestParam int pageSize){
        return departmentService.getAllDepartments(page, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Integer id, @RequestBody DepartmentRequestDto dto){
        return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteDepartment(@PathVariable  Integer id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(Map.of("message", "Department deleted Successfully!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentbyId(@PathVariable  Integer id){
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }
}
