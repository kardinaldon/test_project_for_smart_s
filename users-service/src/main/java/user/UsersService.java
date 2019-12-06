package user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@SpringBootApplication
@EnableJpaRepositories
@Import({RepositoryRestMvcConfiguration.class})
public class UsersService
{
    public static void main( String[] args )
    {
        SpringApplication.run(UsersService.class, args);
    }
}

