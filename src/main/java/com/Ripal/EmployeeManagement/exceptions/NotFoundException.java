package com.Ripal.EmployeeManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1234567894586987458L;
    public  NotFoundException(String message){
        super(message);
    }


}
