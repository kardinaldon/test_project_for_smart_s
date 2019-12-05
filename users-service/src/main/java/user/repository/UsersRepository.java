package user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import user.repository.entity.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "user")
public interface UsersRepository extends CrudRepository<User, Long> {

    public User findById(@Param("id")long id);
}
