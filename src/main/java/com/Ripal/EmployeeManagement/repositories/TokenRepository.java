package com.Ripal.EmployeeManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Ripal.EmployeeManagement.entities.Token;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository <Token, Long> {
     Optional<Token> findByToken(String token);
}
