package evon.api.userauth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto implements Serializable {
    @NotEmpty(message = "Username must not be empty")
    public String username;

    @NotEmpty(message = "Password must not be empty")
    public String password;
}
