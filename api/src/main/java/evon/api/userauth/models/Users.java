package evon.api.userauth.models;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;

@Table(name = "users", schema = "sc_auth")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    @NotEmpty
    public String username;

    @NotEmpty
    public String email;

    @NotEmpty
    public String password;

    public String firstName;

    public String lastName;

    private Integer otp;

    private LocalDateTime otpExpires;

    //    public UserStatus status = UserStatus.ACTIVE;
    public String status;

//    @ManyToMany(fetch = FetchType.EAGER)
//    public List<Roles> roles;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
}
