package front.repository;

import front.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Credentials findByUsername(String username);
}
