package evon.api.userauth.repository;

import evon.api.userauth.models.UsersRoles;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UsersRolesRepository extends R2dbcRepository<UsersRoles, Long> {
}
