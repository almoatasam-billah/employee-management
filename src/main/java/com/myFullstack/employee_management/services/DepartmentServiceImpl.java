package com.myFullstack.employee_management.services;

import com.myFullstack.employee_management.abstracts.DepartmentService;
import com.myFullstack.employee_management.dtos.DepartmentCreate;
import com.myFullstack.employee_management.entities.Department;
import com.myFullstack.employee_management.entities.Employee;
import com.myFullstack.employee_management.repositories.DepartmentRepo;
import com.myFullstack.employee_management.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }


    @Override
    public Department findOne(UUID departmentId) {

        Optional<Department> department = departmentRepo.findById(departmentId);
        if (department.isEmpty()) {
            throw CustomResponseException.resourceNotFound("employee with id " + departmentId + " not found.");
        }


        return department.get();
    }


    @Override
    public Department createOne(DepartmentCreate departmentCreate) {
        Department department = new Department();

        department.setName(departmentCreate.name());

        departmentRepo.save(department);


        return department;

    }

    @Override
    public void deleteOne(UUID departmentId) {

        Optional<Department> department = departmentRepo.findById(departmentId);
        department.ifPresent(value -> departmentRepo.deleteById(value.getId()));

    }

}
