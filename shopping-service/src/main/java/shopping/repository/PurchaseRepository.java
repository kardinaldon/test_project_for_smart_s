package shopping.repository;

import dto.PurchaseDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import shopping.entity.Purchase;

@RepositoryRestResource(collectionResourceRel = "purchases", path = "purchase")
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    public Purchase findById(@Param("id")long id);

    @Override
    public Purchase save(Purchase purchase);
}
