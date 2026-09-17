package com.myFullstack.employee_management.services;

import com.myFullstack.employee_management.abstracts.EmployeeService;
import com.myFullstack.employee_management.dtos.EmployeeCreate;
import com.myFullstack.employee_management.dtos.EmployeeUpdate;
import com.myFullstack.employee_management.entities.Employee;
import com.myFullstack.employee_management.shared.CustomResponseException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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

    @Override
    public Employee findOne(UUID employeeId) {

        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        if (employee.isEmpty()) {
            throw CustomResponseException.resourceNotFound("employee with id " + employeeId + " not found.");
        }


        return employee.get();
    }

    @Override
    public ArrayList<Employee> findAll() {
        return employees;
    }

    @Override
    public void deleteOne(UUID employeeId) {

        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        employee.ifPresent(value -> employees.remove(value));

    }

    @Override
    public Optional<Employee> updateOne(UUID employeeId, EmployeeUpdate employee) {

        Optional<Employee> existingEmployee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        if (existingEmployee.isEmpty()) {
            throw CustomResponseException.resourceNotFound("employee with id " + employeeId + " not found.");
        }

        existingEmployee.get().setFirstName(employee.firstName());
        existingEmployee.get().setLastName(employee.lastName());
        existingEmployee.get().setPhoneNumber(employee.phoneNumber());

        return existingEmployee;
    }

    @Override
    public Employee createOne(EmployeeCreate employeeCreate) {
        Employee employee = new Employee();

        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employee.setFirstName(employeeCreate.firstName());
        employee.setLastName(employeeCreate.lastName());
        employee.setEmail(employeeCreate.email());
        employee.setPhoneNumber(employeeCreate.phoneNumber());
        employee.setHireDate(employeeCreate.hireDate());

        employees.add(employee);

        return employee;
    }
}
