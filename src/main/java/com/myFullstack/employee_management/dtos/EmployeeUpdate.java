package com.myFullstack.employee_management.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EmployeeUpdate(
        @NotNull(message = "first name is required")
        @Size(message = "min is 2 chars and max is 50 chars")
        String firstName,

        @NotNull(message = "last name is required")
        @Size(message = "min is 2 chars and max is 50 chars")
        String lastName,

        @NotNull(message = "phone number name is required")
        String phoneNumber
) {
}
