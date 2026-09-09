package com.myFullstack.employee_management.controllers;

import com.myFullstack.employee_management.entities.Employee;
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
    public ArrayList<Employee> hello() {
        return employees;

    }

    @GetMapping("/{employeeId}")
    public Optional<Employee> findEmployee(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();
        return employee;
    }

    @PostMapping
    public Employee createOne(@RequestBody Employee employee) {

        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);

        return employee;
    }

    @DeleteMapping("/{employeeId}")
    public void deleteOne(@PathVariable UUID employeeId) {

        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        if (employee.isPresent()) {
            employees.remove(employee.get());
        }
    }

    @PutMapping("/{employeeId}")
    public Employee updateOne(@PathVariable UUID employeeId,
                              @RequestBody Employee employee) {

        Optional<Employee> existingEmployee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        if (existingEmployee.isPresent()) {
            existingEmployee.get().setFirstName(employee.getFirstName());
            existingEmployee.get().setLastName(employee.getLastName());
            existingEmployee.get().setEmail(employee.getEmail());
            existingEmployee.get().setPhoneNumber(employee.getPhoneNumber());
            existingEmployee.get().setHireDate(employee.getHireDate());

        }
        return employee;
    }

}

