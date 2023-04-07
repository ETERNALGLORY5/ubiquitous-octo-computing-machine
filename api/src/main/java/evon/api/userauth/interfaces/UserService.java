package evon.api.userauth.interfaces;

import evon.api.userauth.dto.*;
import evon.api.userauth.models.Users;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public interface UserService {

    @PostMapping("/signup")
    @ResponseBody
    public Mono<SignUpResponseDto> signup(@Valid @RequestBody SignUpRequestDto signUpRequestDto);

    @PostMapping("/signin")
    @ResponseBody
    public Mono<SignInResponseDto> signin(@Valid @RequestBody Mono<SignInRequestDto> signInRequestDto);

    @PostMapping("/forget-password")
    @ResponseBody
    public Mono<UserDetailsDto> forgetPassword(@Valid @RequestBody ForgetPasswordRequestDto forgetPasswordRequestDto);

    @PostMapping("/verify-otp")
    @ResponseBody
    public Mono<VerifyOtpResponseDto> verifyOtp(@Valid @RequestBody VerifyOtpRequestDto verifyOtpRequestDto);

    @PostMapping("/reset-password")
    @ResponseBody
    public Mono<UserDetailsDto> setPassword(@Valid @RequestBody ResetPasswordRequestDto resetPasswordRequestDto);

    @PostMapping("/resend-otp")
    @ResponseBody
    public Mono<UserDetailsDto> resendOtp(@Valid @RequestBody ForgetPasswordRequestDto forgetPasswordRequestDto);

    @GetMapping("/profile")
    @ResponseBody
    public Mono<UserDetailsDto> getDetails(Authentication authentication);

    @GetMapping("/get-all")
    @ResponseBody
    public Flux<Users> getAllUsers();

}
