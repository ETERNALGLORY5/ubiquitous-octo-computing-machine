package evon.api.userauth.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

//@Entity
@Table(name = "users_roles", schema = "sc_auth")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersRoles {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    @NotEmpty
    public Integer roleId;

    @NotEmpty
    public Long userId;
}
