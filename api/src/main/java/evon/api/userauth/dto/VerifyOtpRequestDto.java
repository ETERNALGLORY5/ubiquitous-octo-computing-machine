package evon.api.userauth.dto;

import evon.util.validators.OneNotNull;
import jakarta.validation.constraints.*;
import lombok.Data;

@OneNotNull(
        fields = {"username","email"},
        message="Either username or email must be set"
)
@Data
public class VerifyOtpRequestDto {

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Email must be a well-formed email address",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    public String email;

    public String username;

    @NotNull(message = "Otp must not be empty")
    public Integer otp;
}
