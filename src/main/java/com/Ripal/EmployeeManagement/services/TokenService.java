package com.Ripal.EmployeeManagement.services;


import com.Ripal.EmployeeManagement.configuration.JwtHelper;
import com.Ripal.EmployeeManagement.entities.Token;
import com.Ripal.EmployeeManagement.models.EmployeeRequestDto;
import com.Ripal.EmployeeManagement.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private JwtHelper jwtHelper;
    public String generateAndSaveToken(UserDetails userDetails){
        String tokenGenerated= jwtHelper.generateToken(userDetails);



                return "";
    }

}
