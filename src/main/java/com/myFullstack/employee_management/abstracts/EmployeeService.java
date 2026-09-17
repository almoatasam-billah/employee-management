package com.myFullstack.employee_management.abstracts;

import com.myFullstack.employee_management.dtos.EmployeeCreate;
import com.myFullstack.employee_management.dtos.EmployeeUpdate;
import com.myFullstack.employee_management.entities.Employee;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface EmployeeService {

    Employee findOne(UUID employeeId);

    ArrayList<Employee> findAll();

    void deleteOne(UUID employeeId);

    Optional<Employee> updateOne(UUID employeeId, EmployeeUpdate employee);

    Employee createOne(EmployeeCreate employee);
}
