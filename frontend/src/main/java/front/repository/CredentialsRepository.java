package front.repository;

import front.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials, Integer> {

    Optional<Credentials> findByUserName(String userName);
}
