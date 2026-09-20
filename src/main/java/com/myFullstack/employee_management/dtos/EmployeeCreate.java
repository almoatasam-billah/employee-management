package com.myFullstack.employee_management.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record EmployeeCreate(

        @NotNull(message = "first name is required")
        @Size(message = "min is 2 chars and max is 50 chars")
        String firstName,

        @NotNull(message = "last name is required")
        @Size(message = "min is 2 chars and max is 50 chars")
        String lastName,

        @NotNull(message = "Email name is required")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "phone number name is required")
        String phoneNumber,

        @NotNull(message = "hire date is required")
        @PastOrPresent(message = "hire date can not be in the future")
        LocalDate hireDate,

        @NotNull(message = "department id is required")
        UUID departmentId

) {
}
