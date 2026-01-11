package noah.com.noahtaskapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginUserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Valid email required")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min=8,max=16,message = "password should be between 8 and 16 characters")
    private String password;

}
