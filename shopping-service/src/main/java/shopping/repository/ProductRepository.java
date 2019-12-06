package shopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import shopping.entity.Product;

@RepositoryRestResource(collectionResourceRel = "products", path = "product")
public interface ProductRepository extends CrudRepository<Product, Long> {

    public Product findById(@Param("id")long id);

}
