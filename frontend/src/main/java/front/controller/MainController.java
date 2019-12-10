package front.controller;

import constant.PathConstants;
import dto.PurchaseDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(PathConstants.FRONTEND_REST)
@Api(value="Test API", description="Test description")
public class MainController {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${shopping.service.url}")
    private String shoppingServiceUrl;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @GetMapping(path = PathConstants.FRONTEND_SPECIFIC_CUSTOMER_PURCHASES,
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PurchaseDto[]> getCustomerPurchases(@RequestParam int id) {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id",String.valueOf(id));
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(shoppingServiceUrl
                + PathConstants.SHOPPING_SERVICE_PREFIX
                + PathConstants.SHOPPING_SERVICE_SPECIFIC_CUSTOMER_PURCHASES)
                .queryParams(params);
        UriComponents uriComponents = builder.build().encode();
        ResponseEntity<PurchaseDto[]> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
                null, PurchaseDto[].class);
        if (responseEntity.hasBody()) {
            return responseEntity;
        } else {
            return new ResponseEntity<PurchaseDto[]>(HttpStatus.NOT_FOUND);
        }
    }
}
