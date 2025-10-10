package com.Ripal.EmployeeManagement.controllers;

import com.Ripal.EmployeeManagement.entities.Employee;
import com.Ripal.EmployeeManagement.models.EmployeeRequestDto;
import com.Ripal.EmployeeManagement.models.EmployeeResponse;
import com.Ripal.EmployeeManagement.models.EmployementType;
import com.Ripal.EmployeeManagement.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody @Valid EmployeeRequestDto dto){
        return ResponseEntity.ok(employeeService.createEmployee(dto));
    }

    @GetMapping
    public Page<Employee> getAllEmployee(@RequestParam String name, @RequestParam EmployementType employeementType, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize){
        return employeeService.getAllEmployee(name,employeementType, page, pageSize);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeDto){
        Employee updatedEmployee = employeeService.updateEmployee(id,employeeDto);
        return updatedEmployee;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(Map.of("status", HttpStatus.OK , "message", "Employee with Id: "+ id+ " is deleted successfully"));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<EmployeeResponse>> findAllEmployees(@RequestParam Integer page, @RequestParam Integer pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(employeeService.findAllEmployees(pageable));
    }

    @GetMapping("/simple")
    public String getSimple(){
        return "THis is from simple";
    }

}
