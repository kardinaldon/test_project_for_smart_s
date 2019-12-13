package front.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "APP_CREDENTIALS")
public class Credentials implements Serializable {

    @Schema(description = "credentials ID.",
            example = "1", required = false)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="CREDENTIALS_ID", updatable = false)
    private long id;

    @Schema(description = "user ID.",
            example = "1", required = true)
    @Column(name="USER_ID")
    private long userId;

    @Schema(description = "user name.",
            example = "Donald", required = true)
    @Column(name="USERNAME")
    private String userName;

    @Schema(description = "user password in sha256.",
            example = "Donald", required = true)
    @Column(name="USER_PASS")
    private String password;

    @Schema(description = "is the user active.",
            example = "true", required = true)
    @Column(name="ACTIVE")
    private boolean active;

    @Schema(description = "user role in the system.",
            example = "customer", required = true)
    @Column(name="ROLES")
    private String roles;
}


