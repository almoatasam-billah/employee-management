package com.myFullstack.employee_management.controllers;

import com.myFullstack.employee_management.abstracts.DepartmentService;
import com.myFullstack.employee_management.abstracts.EmployeeService;
import com.myFullstack.employee_management.dtos.DepartmentCreate;
import com.myFullstack.employee_management.dtos.EmployeeCreate;
import com.myFullstack.employee_management.entities.Department;
import com.myFullstack.employee_management.entities.Employee;
import com.myFullstack.employee_management.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Department>>> findAll() {

        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(departments), HttpStatus.OK);

    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> findOne(@PathVariable UUID departmentId) {

        Department department = departmentService.findOne(departmentId);
        return new ResponseEntity<>(new GlobalResponse<>(department), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> createOne(@RequestBody @Valid DepartmentCreate departmentCreate) {

        Department department = departmentService.createOne(departmentCreate);

        return new ResponseEntity<Department>(department, HttpStatus.CREATED);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID departmentId) {

        departmentService.deleteOne(departmentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
