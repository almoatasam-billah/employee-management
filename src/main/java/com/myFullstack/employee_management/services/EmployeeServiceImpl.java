package com.myFullstack.employee_management.services;

import com.myFullstack.employee_management.abstracts.EmployeeService;
import com.myFullstack.employee_management.dtos.EmployeeCreate;
import com.myFullstack.employee_management.dtos.EmployeeUpdate;
import com.myFullstack.employee_management.entities.Employee;
import com.myFullstack.employee_management.repositories.EmployeeRepo;
import com.myFullstack.employee_management.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee findOne(UUID employeeId) {

        Optional<Employee> employee = employeeRepo.findById(employeeId);
        if (employee.isEmpty()) {
            throw CustomResponseException.resourceNotFound("employee with id " + employeeId + " not found.");
        }


        return employee.get();
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public void deleteOne(UUID employeeId) {

        Optional<Employee> employee = employeeRepo.findById(employeeId);
        employee.ifPresent(value -> employeeRepo.deleteById(value.getId()));

    }

    @Override
    public Optional<Employee> updateOne(UUID employeeId, EmployeeUpdate employee) {

        Optional<Employee> existingEmployee = employeeRepo.findById(employeeId);

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

        employee.setFirstName(employeeCreate.firstName());
        employee.setLastName(employeeCreate.lastName());
        employee.setEmail(employeeCreate.email());
        employee.setPhoneNumber(employeeCreate.phoneNumber());
        employee.setHireDate(employeeCreate.hireDate());


        employeeRepo.save(employee);


        return employee;
    }
}
