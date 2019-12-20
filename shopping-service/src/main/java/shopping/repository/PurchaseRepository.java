package shopping.repository;

import constant.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shopping.entity.Purchase;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query(value = QueryConstants.PURCHASES_DURING_THE_WEEK,
            nativeQuery = true)
    List<Purchase> findPurchasesDuringTheWeek();

    @Query(nativeQuery = true, value = QueryConstants.MOST_PURCHASED_ITEM_IN_THE_LAST_MONTH)
    String[][] findMostPurchasedItemInTheLastMonth();

    @Query(value = QueryConstants.BEST_CUSTOMER_IN_HALF_YEAR,
            nativeQuery = true)
    String[][] findBestCustomerInHalfYear();

    @Query(value = QueryConstants.MOST_PURCHASED_PRODUCT_BY_EIGHTEEN_YEAR_OLD_CUSTOMERS,
            nativeQuery = true)
    String[][] findMostPurchasedProductByEighteenYearOldCustomers();

    @Query(value = QueryConstants.SPECIFIC_CUSTOMER_PURCHASES,
            nativeQuery = true)
    List<Purchase> findSpecificCustomerPurchases(@Param("id") int id);

}
