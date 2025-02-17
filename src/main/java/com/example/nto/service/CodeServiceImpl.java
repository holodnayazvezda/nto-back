package com.example.nto.service;

import com.example.nto.domain.Code;
import com.example.nto.domain.Employee;
import com.example.nto.repository.CodeRepository;
import com.example.nto.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class CodeServiceImpl {
    private final CodeRepository codeRepository;
    private final EmployeeRepository employeeRepository;

    public CodeServiceImpl(CodeRepository codeRepository, EmployeeRepository employeeRepository) {
        this.codeRepository = codeRepository;
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Code> open(String login, Long value) {
        Code code = codeRepository.getCodeByValue(value);
        Employee employee = employeeRepository.getEmployeesByLogin(login);
        HttpStatus response_status = HttpStatus.OK;

        if (employee == null) response_status = HttpStatus.UNAUTHORIZED;
        else if (code == null) response_status = HttpStatus.BAD_REQUEST;
        if (response_status == HttpStatus.OK) {
            LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            employee.setLastVisit(localDateTime);
            employeeRepository.save(employee);
        }
        return new ResponseEntity<>(code, response_status);
    }
}
