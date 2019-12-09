package user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APP_USER")
@Data
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", updatable = false, nullable = false)
    private long userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_LAST_NAME")
    private String userLastName;

    @Column(name = "USER_AGE")
    private int userAge;


}
