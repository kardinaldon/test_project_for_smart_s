package shopping.controller;

import dto.BestCustomerDto;
import dto.MostPurchasedItemDto;
import dto.PurchaseDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import constant.PathConstants;
import shopping.entity.Purchase;
import shopping.repository.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(PathConstants.SHOPPING_SERVICE_PREFIX)
@AllArgsConstructor
public class ShoppingController {

    @Autowired
    private final PurchaseRepository purchaseRepository;

    @GetMapping(path = PathConstants.SHOPPING_SERVICE_ALL_PURCHASE_PREFIX,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDto> getAllPurchases() {
        ModelMapper modelMapper = new ModelMapper();
        Iterable<Purchase> purchaseRepositoryAll = purchaseRepository.findAll();
        List<PurchaseDto> purchaseDtoList = new ArrayList<>();
        for (Purchase purchase : purchaseRepositoryAll) {
            purchaseDtoList.add(modelMapper.map(purchase, PurchaseDto.class));
        }
        return purchaseDtoList;
    }

    @GetMapping(path = PathConstants.SHOPPING_SERVICE_SPECIFIC_CUSTOMER_PURCHASES,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDto> getSpecificCustomerPurchases(@RequestParam int id) {
        ModelMapper modelMapper = new ModelMapper();
        Iterable<Purchase> purchaseRepositoryAll = purchaseRepository.findSpecificCustomerPurchases(id);
        List<PurchaseDto> purchaseDtoList = new ArrayList<>();
        for (Purchase purchase : purchaseRepositoryAll) {
            purchaseDtoList.add(modelMapper.map(purchase, PurchaseDto.class));
        }
        return purchaseDtoList;
    }

    @GetMapping(path = PathConstants.SHOPPING_SERVICE_PURCHASE_FOR_CERTAIN_PERIOD,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDto> getPurchasesDuringTheWeek() {
        ModelMapper modelMapper = new ModelMapper();
        List<Purchase> weeklyPurchases = purchaseRepository.findPurchasesDuringTheWeek();
        List<PurchaseDto> purchaseDtoList = new ArrayList<>();
        for (Purchase purchase : weeklyPurchases) {
            purchaseDtoList.add(modelMapper.map(purchase, PurchaseDto.class));
        }
        return purchaseDtoList;
    }

    @GetMapping(path = PathConstants.SHOPPING_SERVICE_PURCHASE_MOST_PURCHASED_ITEM_IN_THE_LAST_MONTH,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MostPurchasedItemDto getMostPurchasedItemInTheLastMonth() {
        MostPurchasedItemDto mostPurchasedItemDto = new MostPurchasedItemDto();
        String[][] weeklyPurchases = purchaseRepository.findMostPurchasedItemInTheLastMonth();
        if (weeklyPurchases.length > 0) {
            mostPurchasedItemDto.setPurchaseName(weeklyPurchases[0][0]);
            mostPurchasedItemDto.setCount(Integer.parseInt(weeklyPurchases[0][1]));
            return mostPurchasedItemDto;
        } else {
            mostPurchasedItemDto.setPurchaseName("");
            mostPurchasedItemDto.setCount(0);
            return mostPurchasedItemDto;
        }

    }

    @GetMapping(path = PathConstants.SHOPPING_SERVICE_BEST_CUSTOMER_IN_HALF_YEAR,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BestCustomerDto getBestCustomerInHalfYear() {
        BestCustomerDto bestCustomerDto = new BestCustomerDto();
        String[][] weeklyPurchases = purchaseRepository.findBestCustomerInHalfYear();
        if (weeklyPurchases.length > 0) {
            bestCustomerDto.setBuyersId(Long.parseLong(weeklyPurchases[0][0]));
            bestCustomerDto.setCount(Integer.parseInt(weeklyPurchases[0][1]));
            return bestCustomerDto;
        } else {
            bestCustomerDto.setBuyersId(0);
            bestCustomerDto.setCount(0);
            return bestCustomerDto;
        }

    }

    @GetMapping(path = PathConstants.SHOPPING_SERVICE_MOST_PURCHASED_PRODUCT_BY_EIGHTEEN_YEAR_OLD_CUSTOMERS,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MostPurchasedItemDto getMostPurchasedProductByEighteenYearOldCustomers() {
        MostPurchasedItemDto mostPurchasedItemDto = new MostPurchasedItemDto();
        String[][] weeklyPurchases = purchaseRepository.findMostPurchasedProductByEighteenYearOldCustomers();
        if (weeklyPurchases.length > 0) {
            mostPurchasedItemDto.setPurchaseName(weeklyPurchases[0][0]);
            mostPurchasedItemDto.setCount(Integer.parseInt(weeklyPurchases[0][1]));
            return mostPurchasedItemDto;
        } else {
            mostPurchasedItemDto.setPurchaseName("");
            mostPurchasedItemDto.setCount(0);
            return mostPurchasedItemDto;
        }
    }

    @PostMapping(path = PathConstants.SHOPPING_SERVICE_NEW_PURCHASE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void savePurchase(PurchaseDto purchaseDto) {
        ModelMapper modelMapper = new ModelMapper();
        Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);
        purchaseRepository.save(purchase);
    }

}
