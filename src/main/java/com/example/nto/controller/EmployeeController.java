package com.example.nto.controller;

import com.example.nto.domain.Code;
import com.example.nto.domain.CodeDTO;
import com.example.nto.domain.Employee;
import com.example.nto.service.CodeServiceImpl;
import com.example.nto.service.EmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final CodeServiceImpl codeRepository;
    private final EmployeeServiceImpl employeeRepository;

    public EmployeeController(CodeServiceImpl codeRepository, EmployeeServiceImpl employeeRepository) {
        this.codeRepository = codeRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/{username}/info")
    public ResponseEntity<Employee> info(@PathVariable String username) {
        return employeeRepository.info(username);
    }

    @GetMapping("/{username}/auth")
    public ResponseEntity<Code> auth(@PathVariable String username) {
        return new ResponseEntity<>(null, employeeRepository.auth(username));
    }

    @PatchMapping("/{username}/open")
    public ResponseEntity<Code> open(@PathVariable String username, CodeDTO value) {
        return new ResponseEntity<>(null, codeRepository.open(username, value.getValue()).getStatusCode());
    }
}
