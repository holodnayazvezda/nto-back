package com.example.nto.service;

import com.example.nto.domain.Employee;
import com.example.nto.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class EmployeeServiceImpl {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public HttpStatus auth(String login) {
        Employee employee = employeeRepository.getEmployeesByLogin(login);

        if (employee == null) return HttpStatus.UNAUTHORIZED;
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        employee.setLastVisit(localDateTime);
        employeeRepository.save(employee);

        return HttpStatus.OK;
    }

    public ResponseEntity<Employee> info(String login) {
        Employee employee = employeeRepository.getEmployeesByLogin(login);
        HttpStatus response_status = HttpStatus.OK;

        if (employee == null) response_status = HttpStatus.UNAUTHORIZED;
        else {
            LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            employee.setLastVisit(localDateTime);
            employeeRepository.save(employee);
        }

        return new ResponseEntity<>(employee, response_status);
    }
}
