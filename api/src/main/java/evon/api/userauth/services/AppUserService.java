package evon.api.userauth.services;

import evon.api.userauth.models.Users;
import evon.api.userauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import reactor.core.publisher.Mono;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class AppUserService implements ReactiveUserDetailsService {
    @Autowired
    private UserRepository reactiveUserRepository;
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<Users> userDetails = reactiveUserRepository.findUserByUsername(username);
        return userDetails
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User with username: " + username +" not found !")))
                .map(dt -> {
                    Set<GrantedAuthority> authorities = new HashSet<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    return new org.springframework.security.core.userdetails.User(
                            dt.getUsername(),
                            dt.getPassword(),
                            authorities
                    );
                });
    }
}
