package front.controller;

import constant.PathConstants;
import dto.BestCustomerDto;
import dto.MostPurchasedItemDto;
import dto.PurchaseDto;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@RestController
@RequestMapping(PathConstants.FRONTEND_REST_REPORTS)
public class ReportController {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${shopping.service.url}")
    private String shoppingServiceUrl;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @GetMapping(path = PathConstants.FRONTEND_ALL_PURCHASES_PER_WEEK,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PurchaseDto[]> getAllPurchasesPerWeek() {
        ResponseEntity<PurchaseDto[]> responseEntity = restTemplate.getForEntity(shoppingServiceUrl
                        + PathConstants.SHOPPING_SERVICE_PREFIX
                        + PathConstants.SHOPPING_SERVICE_PURCHASE_FOR_CERTAIN_PERIOD,
                        PurchaseDto[].class);
        if (responseEntity.hasBody()) {
            return responseEntity;
        } else {
            return new ResponseEntity<PurchaseDto[]>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = PathConstants.FRONTEND_PURCHASE_MOST_PURCHASED_ITEM_IN_THE_LAST_MONTH,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MostPurchasedItemDto getMostPurchasedItemLastMonth() {
        MostPurchasedItemDto response = restTemplate.getForObject(shoppingServiceUrl
                        + PathConstants.SHOPPING_SERVICE_PREFIX
                        + PathConstants.SHOPPING_SERVICE_PURCHASE_MOST_PURCHASED_ITEM_IN_THE_LAST_MONTH,
                MostPurchasedItemDto.class);
        return Objects.requireNonNullElseGet(response, () -> new MostPurchasedItemDto("The goods were not in demand", 0));
    }

    @GetMapping(path = PathConstants.FRONTEND_BEST_CUSTOMER_IN_HALF_YEAR,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> getBestCustomerInHalfYear() {
        BestCustomerDto response = restTemplate.getForObject(shoppingServiceUrl
                        + PathConstants.SHOPPING_SERVICE_PREFIX
                        + PathConstants.SHOPPING_SERVICE_BEST_CUSTOMER_IN_HALF_YEAR,
                BestCustomerDto.class);
        if (response != null) {
            LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("id",String.valueOf(response.getBuyersId()));
            var builder = UriComponentsBuilder.fromUriString(userServiceUrl + PathConstants.USER_SERVICE_PREFIX + PathConstants.USER_SERVICE_BY_ID)
                    .queryParams(params);
            UriComponents uriComponents = builder.build().encode();
            ResponseEntity<UserDto> userDto = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
                    null, UserDto.class);
            if (userDto.hasBody()) {
                return userDto;
            } else {
                return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = PathConstants.FRONTEND_MOST_PURCHASED_PRODUCT_BY_EIGHTEEN_YEAR_OLD_CUSTOMERS,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MostPurchasedItemDto getMostBoughtByEighteenYearOldCustomers() {
        MostPurchasedItemDto response = restTemplate.getForObject(shoppingServiceUrl
                        + PathConstants.SHOPPING_SERVICE_PREFIX
                        + PathConstants.SHOPPING_SERVICE_MOST_PURCHASED_PRODUCT_BY_EIGHTEEN_YEAR_OLD_CUSTOMERS,
                MostPurchasedItemDto.class);
        return Objects.requireNonNullElseGet(response, () -> new MostPurchasedItemDto("The store is not in demand by eighteen-year-old customers", 0));
    }

}
