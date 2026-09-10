package com.myFullstack.employee_management.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Employee {

    private UUID id;

    @NotNull(message = "first name is required")
    @Size(message = "min is 2 chars and max is 50 chars")
    private String firstName;

    @NotNull(message = "last name is required")
    @Size(message = "min is 2 chars and max is 50 chars")
    private String lastName;

    @NotNull(message = "Email name is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "phone number name is required")
    private String phoneNumber;

    @NotNull(message = "hire date is required")
    @PastOrPresent(message = "hire date can not be in the future")
    private LocalDate hireDate;
    private UUID departmentId;


}
