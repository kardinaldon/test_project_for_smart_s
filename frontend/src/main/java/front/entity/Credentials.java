package front.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APP_CREDENTIALS")
public class Credentials implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="CREDENTIALS_ID", updatable = false, nullable = false)
    private long credentialsId;

    @Column(name="USER_ID")
    private long userId;

    @Column(name="USERNAME")
    private long username;

    @Column(name="USER_PASS")
    private String password;
}
