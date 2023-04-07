package evon.api.userauth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequestDto {
    @NotBlank(message = "Email must not be empty")
    public String email;

    @NotBlank(message = "Username must not be empty")
    public String username;

    @NotBlank(message = "Password must not be empty")
    public String password;

    @NotBlank(message = "Confirm Password must not be empty")
    public String confirmPassword;

    @NotBlank(message = "Phone Number must not be empty")
    public String phoneNumber;

    @NotBlank(message = "Country must not be empty")
    public String country;

}
