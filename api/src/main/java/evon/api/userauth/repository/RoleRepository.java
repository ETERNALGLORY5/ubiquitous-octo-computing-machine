package evon.api.userauth.repository;

import evon.api.userauth.models.Roles;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface RoleRepository extends R2dbcRepository<Roles, Integer> {
    Mono<Roles> findRoleByName(String name);
}
