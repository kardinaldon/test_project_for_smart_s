package front;

import front.repository.CredentialsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = CredentialsRepository.class)
public class FrontendService {
    public static void main(String[] args) {
        SpringApplication.run(FrontendService.class, args);
    }
}
