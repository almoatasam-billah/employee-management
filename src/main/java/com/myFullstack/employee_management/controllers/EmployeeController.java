package com.myFullstack.employee_management.controllers;

import com.myFullstack.employee_management.abstracts.EmployeeService;
import com.myFullstack.employee_management.entities.Employee;
import com.myFullstack.employee_management.shared.CustomResponseException;
import com.myFullstack.employee_management.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<ArrayList<Employee>>> findAll() {

        ArrayList<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);

    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeId) {

        Employee employee = employeeService.findOne(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createOne(@RequestBody @Valid Employee employee) {

        employeeService.createOne(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId) {

        employeeService.deleteOne(employeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Optional<Employee>> updateOne(@PathVariable UUID employeeId,
                                                        @RequestBody @Valid Employee employee) {

        Optional<Employee> updatedEmployee = employeeService.updateOne(employeeId, employee);

        return new ResponseEntity<Optional<Employee>>(updatedEmployee, HttpStatus.OK);
    }

}

