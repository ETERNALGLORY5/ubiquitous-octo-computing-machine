package evon.api.userauth.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDetailsWithOtpDto {
    private Long id;

    private String email;

    private String username;

    public String firstName;

    public String lastName;

    private Integer otp;

    private LocalDateTime otpExpires;
}
