package YahiaLakrikba.ES_28._6.exeptions;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeePatchRequest {

    @Pattern(regexp = ".*\\S.*", message = "Invalid first name!")
    @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters")
    private String firstName;

    @Pattern(regexp = ".*\\S.*", message = "Invalid last name!")
    @Size(min = 3, max = 15, message = "Surname must be between 3 and 15 characters")
    private String lastName;

    @Pattern(regexp = ".*\\S.*", message = "Invalid username!")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
    private String username;

    @Email(message = "Must be a valid email address")
    private String email;
}
