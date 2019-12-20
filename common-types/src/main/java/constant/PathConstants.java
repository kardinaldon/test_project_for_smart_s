package constant;

public interface PathConstants {

//    Frontend urls
    String FRONTEND_REST = "/front";
    String FRONTEND_REST_REPORTS = "/front_reports";
    String FRONTEND_SPECIFIC_CUSTOMER_PURCHASES = "/customer_purchases";
    String FRONTEND_NEW_PURCHASE = "/purchase_new";
    String FRONTEND_ALL_PURCHASES_PER_WEEK = "/all_purchases_per_week";
    String FRONTEND_PURCHASE_MOST_PURCHASED_ITEM_IN_THE_LAST_MONTH = "/top_purchase_last_month";
    String FRONTEND_BEST_CUSTOMER_IN_HALF_YEAR = "/best_customer_half_year";
    String FRONTEND_MOST_PURCHASED_PRODUCT_BY_EIGHTEEN_YEAR_OLD_CUSTOMERS = "/purchased_product_eighteen_customers";

//    Shopping service urls
    String SHOPPING_SERVICE_PREFIX = "/shopping_service_rest";
    String SHOPPING_SERVICE_NEW_PURCHASE = "/purchase";
    String SHOPPING_SERVICE_ALL_PURCHASE_PREFIX = "/all_purchases";
    String SHOPPING_SERVICE_PURCHASE_FOR_CERTAIN_PERIOD = "/purchases_certain_period";
    String SHOPPING_SERVICE_PURCHASE_MOST_PURCHASED_ITEM_IN_THE_LAST_MONTH = "/top_last_month";
    String SHOPPING_SERVICE_BEST_CUSTOMER_IN_HALF_YEAR = "/best_customer_half_year";
    String SHOPPING_SERVICE_MOST_PURCHASED_PRODUCT_BY_EIGHTEEN_YEAR_OLD_CUSTOMERS = "/purchased_product_eighteen_customers";
    String SHOPPING_SERVICE_SPECIFIC_CUSTOMER_PURCHASES = "/specific_customer_purchases";

//    User service urls
    String USER_SERVICE_PREFIX = "/user_service_rest";
    String USER_SERVICE_BY_ID = "/user_by_id";

//    Authentication url

    String FRONTEND_AUTH = "/auth";
    String FRONTEND_AUTH_API = "/authenticator";
}
