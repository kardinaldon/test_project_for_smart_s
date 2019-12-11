package user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "APP_USER")
@Data
@EqualsAndHashCode
public class User implements Serializable {

    @Schema(description = "user ID.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", updatable = false, nullable = false)
    private long userId;

    @Schema(description = "user name.",
            example = "Donald", required = true)
    @Size(min = 1, max = 300)
    @Column(name = "USER_NAME")
    private String userName;

    @Schema(description = "user last name.",
            example = "Ivanov", required = true)
    @Size(min = 1, max = 300)
    @Column(name = "USER_LAST_NAME")
    private String userLastName;

    @Schema(description = "user age.",
            example = "18", required = true)
    @Column(name = "USER_AGE")
    private int userAge;


}
