package shopping.constant;

public interface QueryConstants {

    String PURCHASES_DURING_THE_WEEK = "SELECT * FROM APP_PURCHASE WHERE purchase_date > (NOW() - INTERVAL '7 DAY')";
    String MOST_PURCHASED_ITEM_IN_THE_LAST_MONTH = "SELECT (APP_PURCHASE.purchase_name) AS text, count(*) " +
            "FROM APP_PURCHASE WHERE APP_PURCHASE.purchase_date > (NOW() - INTERVAL '30 DAY') " +
            "GROUP BY APP_PURCHASE.purchase_name HAVING count(*) > 1 ORDER BY 2 DESC LIMIT 1";
    String BEST_CUSTOMER_IN_HALF_YEAR = "SELECT (APP_PURCHASE.buyers_id) AS text, count(*) " +
            "FROM APP_PURCHASE WHERE APP_PURCHASE.purchase_date > (NOW() - INTERVAL '6 MONTHS') " +
            "GROUP BY APP_PURCHASE.buyers_id HAVING count(*) > 1 ORDER BY 2 DESC LIMIT 1";
    String MOST_PURCHASED_PRODUCT_BY_EIGHTEEN_YEAR_OLD_CUSTOMERS = "SELECT (APP_PURCHASE.purchase_name) AS text, count(*) " +
            "FROM APP_PURCHASE WHERE APP_PURCHASE.buyers_id IN (2,3) " +
            "GROUP BY APP_PURCHASE.purchase_name HAVING count(*) > 1 ORDER BY 2 DESC LIMIT 1";

}
