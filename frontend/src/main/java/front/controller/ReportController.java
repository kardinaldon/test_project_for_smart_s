package front.controller;

import constant.PathConstants;
import dto.BestCustomerDto;
import dto.MostPurchasedItemDto;
import dto.PurchaseDto;
import dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(PathConstants.FRONTEND_REST_REPORTS)
@Tag(name = "Reports", description = "report generation API")
public class ReportController {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${shopping.service.url}")
    private String shoppingServiceUrl;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Operation(summary = "Find all purchases per week", description = "all purchases product per week", tags = {"Reports"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PurchaseDto.class)))),
            @ApiResponse(responseCode = "404", description = "Purchases not found")})
    @GetMapping(path = PathConstants.FRONTEND_ALL_PURCHASES_PER_WEEK,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PurchaseDto[]> getAllPurchasesPerWeek() {
        try {

            return restTemplate.getForEntity(shoppingServiceUrl
                            + PathConstants.SHOPPING_SERVICE_PREFIX
                            + PathConstants.SHOPPING_SERVICE_PURCHASE_FOR_CERTAIN_PERIOD,
                    PurchaseDto[].class);
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<PurchaseDto[]>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Find most purchased item last month", description = "most purchased product in last month", tags = {"Reports"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MostPurchasedItemDto.class)))),
            @ApiResponse(responseCode = "404", description = "Purchased Item not found")})
    @GetMapping(path = PathConstants.FRONTEND_PURCHASE_MOST_PURCHASED_ITEM_IN_THE_LAST_MONTH,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getMostPurchasedItemLastMonth() {
        try {
            ResponseEntity responseEntity = restTemplate.getForEntity(shoppingServiceUrl
                            + PathConstants.SHOPPING_SERVICE_PREFIX
                            + PathConstants.SHOPPING_SERVICE_PURCHASE_MOST_PURCHASED_ITEM_IN_THE_LAST_MONTH,
                    MostPurchasedItemDto.class);
            return responseEntity;
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }

    }

    @Operation(summary = "Find the best customer in half year", description = "the best customer in half year", tags = {"Reports"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "the best customer in half year not found")})
    @GetMapping(path = PathConstants.FRONTEND_BEST_CUSTOMER_IN_HALF_YEAR,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> getBestCustomerInHalfYear() {
        try {
            BestCustomerDto response = restTemplate.getForObject(shoppingServiceUrl
                            + PathConstants.SHOPPING_SERVICE_PREFIX
                            + PathConstants.SHOPPING_SERVICE_BEST_CUSTOMER_IN_HALF_YEAR,
                    BestCustomerDto.class);
            try {
                LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("id", String.valueOf(response.getBuyersId()));
                var builder = UriComponentsBuilder.fromUriString(userServiceUrl + PathConstants.USER_SERVICE_PREFIX + PathConstants.USER_SERVICE_BY_ID)
                        .queryParams(params);
                UriComponents uriComponents = builder.build().encode();
                return restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
                        null, UserDto.class);
            } catch (HttpStatusCodeException e) {
                return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
            }

        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Find most bought by eighteen year old customers", description = "most bought by eighteen year old customers", tags = {"Reports"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MostPurchasedItemDto.class)))),
            @ApiResponse(responseCode = "404", description = "most bought by eighteen year old customers not found")})
    @GetMapping(path = PathConstants.FRONTEND_MOST_PURCHASED_PRODUCT_BY_EIGHTEEN_YEAR_OLD_CUSTOMERS,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getMostBoughtByEighteenYearOldCustomers() {
        try {
            ResponseEntity response = restTemplate.getForEntity(shoppingServiceUrl
                            + PathConstants.SHOPPING_SERVICE_PREFIX
                            + PathConstants.SHOPPING_SERVICE_MOST_PURCHASED_PRODUCT_BY_EIGHTEEN_YEAR_OLD_CUSTOMERS,
                    MostPurchasedItemDto.class);
            return response;
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }
}
