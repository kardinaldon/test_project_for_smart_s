package shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shopping.entity.Purchase;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query(value = "SELECT * FROM APP_PURCHASE WHERE purchase_date > (NOW() - INTERVAL '7 DAY')",
            nativeQuery = true)
    List<Purchase> findPurchasesDuringTheWeek();

    @Query(nativeQuery = true, value = "SELECT (APP_PURCHASE.purchase_name) AS text, count(*) " +
            "FROM APP_PURCHASE WHERE APP_PURCHASE.purchase_date > (NOW() - INTERVAL '30 DAY') " +
            "GROUP BY APP_PURCHASE.purchase_name HAVING count(*) > 1 ORDER BY 2 DESC LIMIT 1")
    String[][] mostPurchasedItemInTheLastMonth();

    @Query(value = "SELECT (APP_PURCHASE.buyers_id) AS text, count(*) " +
            "FROM APP_PURCHASE WHERE APP_PURCHASE.purchase_date > (NOW() - INTERVAL '6 MONTHS') " +
            "GROUP BY APP_PURCHASE.buyers_id HAVING count(*) > 1 ORDER BY 2 DESC LIMIT 1;",
            nativeQuery = true)
    String[][] findBestCustomerInHalfYear();
}
