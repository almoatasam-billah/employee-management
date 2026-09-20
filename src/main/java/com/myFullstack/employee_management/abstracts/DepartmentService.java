package com.myFullstack.employee_management.abstracts;

import com.myFullstack.employee_management.dtos.DepartmentCreate;
import com.myFullstack.employee_management.entities.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    Department findOne(UUID departmentId);

    List<Department> findAll();

    Department createOne(DepartmentCreate departmentCreate);

    void deleteOne(UUID departmentId);
}
