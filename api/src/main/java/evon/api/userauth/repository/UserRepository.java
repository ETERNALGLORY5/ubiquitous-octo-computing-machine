package evon.api.userauth.repository;

import evon.api.userauth.models.Users;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
//@Repository
public interface UserRepository extends R2dbcRepository<Users, Long> {
    Mono<Users> findUserByEmail(String email);

    Mono<Users> findUserByUsername(String username);

    @Query("SELECT * FROM users WHERE username = :username OR email = :email")
    Mono<Users> findUserByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

    Mono<Optional<Users>> findUserByUsernameAndOtp(String username, Integer otp);

    Mono<Optional<Users>> findUserByEmailAndOtp(String email, Integer otp);

    @Query("SELECT * FROM users WHERE (username = :username OR email = :email) AND otp= :otp")
    Mono<Users> findUserByUsernameOrEmailAndOtp(@Param("username") String username, @Param("email") String email, @Param("otp") Integer otp);

}
