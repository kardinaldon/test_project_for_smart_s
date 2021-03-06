package front.controller;

import constant.PathConstants;
import dto.PurchaseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(PathConstants.FRONTEND_REST)
@Tag(name = "Main user controller", description = "controller where you can get the current user's shopping list and create a new purchase")
public class MainController {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${shopping.service.url}")
    private String shoppingServiceUrl;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Operation(summary = "Find purchases for a specific user", description = "access is protected using spring security (basic), with correct access the shopping-service microservice is accessed with the received \"user ID\", the answer will be the current user's shopping list", tags = {"Main user controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PurchaseDto.class)))),
            @ApiResponse(responseCode = "404", description = "purchases for a specific user not found")})
    @GetMapping(path = PathConstants.FRONTEND_SPECIFIC_CUSTOMER_PURCHASES + "/{id}",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object[]> getCustomerPurchases(@Parameter(description="path parameter user ID") @PathVariable String id) {
        try {
            LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("id",id);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(shoppingServiceUrl
                    + PathConstants.SHOPPING_SERVICE_PREFIX
                    + PathConstants.SHOPPING_SERVICE_SPECIFIC_CUSTOMER_PURCHASES)
                    .queryParams(params);
            UriComponents uriComponents = builder.build().encode();
            ResponseEntity<Object[]> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
                    null, Object[].class);
            return responseEntity;
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<Object[]>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create new purchase", description = "access is protected by spring security (basic), with correct access the shopping-service microservice is accessed with the received Request Body purchase (Content-Type application / xml), verification of the received data by xsd schema, with the correct creation of a new HttpStatus.OK will be sent in response to the purchase, in case of failure, an error body will be sent with a description of incorrectly entered data", tags = {"Main user controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase created",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PurchaseDto.class)))),
            @ApiResponse(responseCode = "500", description = "failed to create a new purchase"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "415", description = "failed to create a new purchase,because Unsupported Media Type sent")})
    @PostMapping(path = PathConstants.FRONTEND_NEW_PURCHASE, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity savePurchase(@RequestBody PurchaseDto purchaseDto) {
        try {
            restTemplate.postForObject(shoppingServiceUrl
                            + PathConstants.SHOPPING_SERVICE_PREFIX
                            + PathConstants.SHOPPING_SERVICE_NEW_PURCHASE, purchaseDto,
                    ResponseEntity.class);
            return new ResponseEntity(HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }
}
