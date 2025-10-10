package com.Ripal.EmployeeManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tokens")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String token;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable = false)
    private Employee employee;

    @Column(nullable=false)
    private LocalDate timeCreated;


}
