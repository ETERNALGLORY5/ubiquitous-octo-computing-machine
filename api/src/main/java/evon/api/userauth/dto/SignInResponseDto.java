package evon.api.userauth.dto;

import lombok.Data;

@Data
public class SignInResponseDto {
    public String token;

    public UserDetailsDto userDetails;
}
