package com.Ripal.EmployeeManagement.models;

import com.Ripal.EmployeeManagement.entities.Department;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {
    @NotBlank(message="Employee name should not be empty")
    private String name;

    @NotNull(message="Employement type should not be null")
    @Enumerated(EnumType.STRING)
    private EmployementType employementType;

    @NotNull(message="Employee should frm anyone department")
    private String departmentName;

    @NotNull(message="Employee should have salary")
    private Double salary;

    @NotNull(message="Username should not be blank")
    private String userName;

    @NotNull(message="Password should not be blank")
    private String password;

}
