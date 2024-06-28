package YahiaLakrikba.ES_28._6.exeptions;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRequest {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 4, max = 10, message = "Name must be between 4 and 10 characters")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    @Size(min = 4, max = 10, message = "Surname must be between 4 and 10 characters")
    private String sureName;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 4, max = 10, message = "Username must be between 4 and 10 characters")
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Must be a valid email address")
    private String email;
}
