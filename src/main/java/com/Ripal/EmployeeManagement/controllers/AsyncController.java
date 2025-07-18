package com.Ripal.EmployeeManagement.controllers;

import com.Ripal.EmployeeManagement.services.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/async")
public class AsyncController {
    private final AsyncService asyncService;
    @GetMapping
    public ResponseEntity<String> startAsyncProgramming(){
        asyncService.asyncprogramming();
        return ResponseEntity.ok("Process Started");
    }
}
