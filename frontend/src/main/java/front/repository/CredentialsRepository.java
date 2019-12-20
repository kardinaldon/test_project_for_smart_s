package front.repository;

import front.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Credentials findByUserName(String userName);

}
