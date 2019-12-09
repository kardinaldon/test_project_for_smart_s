package user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import user.entity.User;

import java.util.List;

@Repository
@Transactional
public interface UsersRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.userAge = ?1")
    List<User> findByAge(int age);
}
