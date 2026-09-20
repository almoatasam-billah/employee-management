package com.myFullstack.employee_management.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentCreate(

        @NotNull(message = "name is required")
        @Size(message = "min is 2 chars and max is 50 chars")
        String name

) {
}
