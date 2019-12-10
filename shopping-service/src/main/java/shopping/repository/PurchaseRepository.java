package shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shopping.constant.QueryConstants;
import shopping.entity.Purchase;

import java.util.List;

@Repository
@Transactional
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
}
