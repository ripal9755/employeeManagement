package com.Ripal.EmployeeManagement.models;

import com.Ripal.EmployeeManagement.entities.Employee;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequestDto {

    private Long id;

    @NotNull(message="Token should not blank, There might issue in token generation")
    private String token;

    private LocalDate dateCreated;

    @NotNull(message = "We can not get Employee id, that should not blank")
    private Employee employee;
}
