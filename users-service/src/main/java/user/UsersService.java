package user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class UsersService
{
    public static void main( String[] args )
    {
        SpringApplication.run(UsersService.class, args);
    }
}

