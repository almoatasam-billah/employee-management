package com.myFullstack.employee_management.controllers;

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

    ArrayList<Employee> employees = new ArrayList<>(List.of(
            new Employee(
                    UUID.randomUUID(),
                    "Max",
                    "Mustermann",
                    "max@example.com",
                    "017612345678",
                    LocalDate.of(2023, 5, 10),
                    UUID.randomUUID()
            ),
            new Employee(
                    UUID.randomUUID(),
                    "Anna",
                    "Schmidt",
                    "anna@example.com",
                    "015112345679",
                    LocalDate.of(2024, 1, 15),
                    UUID.randomUUID()
            )));


    @GetMapping
    public ResponseEntity<GlobalResponse<ArrayList<Employee>>> findAll() {

        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);

    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findEmployee(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        if (employee.isEmpty()) {
            throw CustomResponseException.resourceNotFound("employee with id " + employeeId + " not found.");
        }
        return new ResponseEntity<>(new GlobalResponse<>(employee.get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createOne(@RequestBody @Valid Employee employee) {

        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId) {

        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        if (employee.isPresent()) {
            employees.remove(employee.get());
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Optional<Employee>> updateOne(@PathVariable UUID employeeId,
                                                        @RequestBody @Valid Employee employee) {

        Optional<Employee> existingEmployee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        if (existingEmployee.isEmpty()) {
            throw CustomResponseException.resourceNotFound("employee with id " + employeeId + " not found.");
        }

        existingEmployee.get().setFirstName(employee.getFirstName());
        existingEmployee.get().setLastName(employee.getLastName());
        existingEmployee.get().setEmail(employee.getEmail());
        existingEmployee.get().setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.get().setHireDate(employee.getHireDate());


        return new ResponseEntity<Optional<Employee>>(existingEmployee, HttpStatus.OK);
    }

}

