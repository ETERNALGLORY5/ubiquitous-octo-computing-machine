package evon.api.userauth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import evon.api.userauth.models.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
public class JwtTokenService {
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(120);

    private final Algorithm hmac512;
    private final JWTVerifier verifier;

    public JwtTokenService(@Value("${jwt.secret}") final String secret) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(this.hmac512).build();
    }

    public String generateToken(final Users userDetails) {
        final Instant now = Instant.now();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(this.hmac512);
    }

    public Mono<String> generateReactiveToken (final Users userDetails) {
        final Instant now = Instant.now();
        return Mono.just(JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(this.hmac512));
    }

    public String generateToken(String name) {
        final Instant now = Instant.now();
        return JWT.create()
                .withSubject(name)
                .withIssuer("app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(this.hmac512);
    }

    public String generateToken(String name, Integer minutes) {
        final Instant now = Instant.now();
        return JWT.create()
                .withSubject(name)
                .withIssuer("app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(Duration.ofMinutes(minutes).toMillis()))
                .sign(this.hmac512);
    }

    public Boolean validateToken(final String token) {
        try {
            return !verifier.verify(token).getSubject().isEmpty();
        } catch (final JWTVerificationException verificationEx) {
            return false;
        }
    }

    public String validateTokenAndGetUsername(final String token) {
        try {
            return verifier.verify(token).getSubject();
        } catch (final JWTVerificationException verificationEx) {
            return null;
        }
    }

}
