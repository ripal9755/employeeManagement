package com.Ripal.EmployeeManagement.entities;

import com.Ripal.EmployeeManagement.models.EmployementType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //@NotBlank(message="Employee name can not be null or empty")
    @Column(nullable = false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    private String name;

    @Column(name="is_deleted")
    private boolean isDeleted;
  //  @NotBlank(message="Employement type can not be empty")
    @Enumerated(EnumType.STRING)
    private EmployementType employeementType;


   // @NotBlank(message = "Depart name can not be null or empty")
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="department_id", unique = false)
    private Department department;

    private double salary;

}
