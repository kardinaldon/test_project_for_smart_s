package shopping.controller;

import constant.PathConstants;
import dto.BestCustomerDto;
import dto.MostPurchasedItemDto;
import dto.PurchaseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.entity.Purchase;
import shopping.repository.PurchaseRepository;

import java.util.List;

@RestController
@RequestMapping(PathConstants.SHOPPING_SERVICE_PREFIX)
@AllArgsConstructor
@Tag(name = "Shopping Controller", description = "API for CRUD purchases, reporting")
public class ShoppingController {

    @Autowired
    private final PurchaseRepository purchaseRepository;

    @Operation(summary = "Find all purchases", description = "all purchases", tags = {"Shopping Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PurchaseDto.class)))),
            @ApiResponse(responseCode = "404", description = "Purchases not found")})
    @GetMapping(path = PathConstants.SHOPPING_SERVICE_ALL_PURCHASE_PREFIX,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getAllPurchases() {
        try {
            Iterable<Purchase> purchaseRepositoryAll = purchaseRepository.findAll();
            return new ResponseEntity<Object>(purchaseRepositoryAll, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Find specific customer purchases", description = "specific customer purchases", tags = {"Shopping Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PurchaseDto.class)))),
            @ApiResponse(responseCode = "404", description = "Purchases for a specific customer not found")})
    @GetMapping(path = PathConstants.SHOPPING_SERVICE_SPECIFIC_CUSTOMER_PURCHASES,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getSpecificCustomerPurchases(@Parameter(description="user ID") @RequestParam int id) {
        try {
            Iterable<Purchase> purchaseList = purchaseRepository.findSpecificCustomerPurchases(id);
            return new ResponseEntity<Object>(purchaseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Find purchases during the week", description = "purchases during the week", tags = {"Shopping Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PurchaseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No purchases found within a week")})
    @GetMapping(path = PathConstants.SHOPPING_SERVICE_PURCHASE_FOR_CERTAIN_PERIOD,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getPurchasesDuringTheWeek() {
        try {
            List<Purchase> weeklyPurchases = purchaseRepository.findPurchasesDuringTheWeek();
            return new ResponseEntity<Object>(weeklyPurchases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Find most purchased item in the last month", description = "most purchased item in the last month", tags = {"Shopping Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MostPurchasedItemDto.class)))),
            @ApiResponse(responseCode = "404", description = "The most purchased product in the last month was not found")})
    @GetMapping(path = PathConstants.SHOPPING_SERVICE_PURCHASE_MOST_PURCHASED_ITEM_IN_THE_LAST_MONTH,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getMostPurchasedItemInTheLastMonth() {
        try {
            MostPurchasedItemDto mostPurchasedItemDto = new MostPurchasedItemDto();
            String[][] item = purchaseRepository.findMostPurchasedItemInTheLastMonth();
            if (item.length > 0) {
                mostPurchasedItemDto.setPurchaseName(item[0][0]);
                mostPurchasedItemDto.setCount(Integer.parseInt(item[0][1]));
                return new ResponseEntity(mostPurchasedItemDto, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Find the best customer for half a year", description = "best customer for half a year", tags = {"Shopping Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BestCustomerDto.class)))),
            @ApiResponse(responseCode = "404", description = "Best customer for half a year not found")})
    @GetMapping(path = PathConstants.SHOPPING_SERVICE_BEST_CUSTOMER_IN_HALF_YEAR,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getBestCustomerInHalfYear() {
        try {
            BestCustomerDto bestCustomerDto = new BestCustomerDto();
            String[][] bestCustomerInHalfYear = purchaseRepository.findBestCustomerInHalfYear();
            if (bestCustomerInHalfYear.length > 0) {
                bestCustomerDto.setBuyersId(Long.parseLong(bestCustomerInHalfYear[0][0]));
                bestCustomerDto.setCount(Integer.parseInt(bestCustomerInHalfYear[0][1]));
                return new ResponseEntity(bestCustomerDto, HttpStatus.OK);
            } else {
                bestCustomerDto.setBuyersId(0);
                bestCustomerDto.setCount(0);
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Find the best-selling product", description = "The most purchased item", tags = {"Shopping Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MostPurchasedItemDto.class)))),
            @ApiResponse(responseCode = "404", description = "The most purchased item not found")})
    @GetMapping(path = PathConstants.SHOPPING_SERVICE_MOST_PURCHASED_PRODUCT_BY_EIGHTEEN_YEAR_OLD_CUSTOMERS,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getMostPurchasedProductByEighteenYearOldCustomers() {
        try {
            MostPurchasedItemDto mostPurchasedItemDto = new MostPurchasedItemDto();
            String[][] mostPurchased = purchaseRepository.findMostPurchasedProductByEighteenYearOldCustomers();
            if (mostPurchased.length > 0) {
                mostPurchasedItemDto.setPurchaseName(mostPurchased[0][0]);
                mostPurchasedItemDto.setCount(Integer.parseInt(mostPurchased[0][1]));
                return new ResponseEntity(mostPurchasedItemDto, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Create new purchase", description = "new purchase", tags = {"Shopping Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase was created",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PurchaseDto.class)))),
            @ApiResponse(responseCode = "500", description = "failed to create a new purchase"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "415", description = "failed to create a new purchase,because Unsupported Media Type was sent")})
    @PostMapping(path = PathConstants.SHOPPING_SERVICE_NEW_PURCHASE, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity savePurchase(@RequestBody PurchaseDto purchaseDto) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);
            purchaseRepository.save(purchase);
            return new ResponseEntity(purchase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
