package user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import user.entity.User;

@Transactional(readOnly = true)
@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

}
