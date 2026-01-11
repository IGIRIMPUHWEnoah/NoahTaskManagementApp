package noah.com.noahtaskapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "User name is Required")
    @Size(max=255,message = "Name must be less than 255 characters")
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min=6, max=16,message = "It must be between  8 and 16 characters")
    private String password;

}
