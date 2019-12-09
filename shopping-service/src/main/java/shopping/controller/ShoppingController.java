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
import shopping.entity.Purchase;
import shopping.repository.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${shopping.service.prefix}")
@AllArgsConstructor
public class ShoppingController {

    @Autowired
    private final PurchaseRepository purchaseRepository;

    @PostMapping(path = "${shopping.service.new.purchase}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void savePurchase(PurchaseDto purchaseDto) {
        ModelMapper modelMapper = new ModelMapper();
        Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);
        purchaseRepository.save(purchase);
    }

    @GetMapping(path = "${shopping.service.all.purchases.prefix}",
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

    @GetMapping(path = "${shopping.service.purchases.for.certain.period}",
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

    @GetMapping(path = "${shopping.service.purchases.most.purchased.item.in.the.last.month}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MostPurchasedItemDto getMostPurchasedItemInTheLastMonth() {
        MostPurchasedItemDto mostPurchasedItemDto = new MostPurchasedItemDto();
        String[][] weeklyPurchases = purchaseRepository.mostPurchasedItemInTheLastMonth();
        if(weeklyPurchases.length > 0){
            mostPurchasedItemDto.setPurchaseName(weeklyPurchases[0][0]);
            mostPurchasedItemDto.setCount(Integer.parseInt(weeklyPurchases[0][1]));
            return mostPurchasedItemDto;
        } else {
            mostPurchasedItemDto.setPurchaseName("");
            mostPurchasedItemDto.setCount(0);
            return mostPurchasedItemDto;
        }

    }

    @GetMapping(path = "${shopping.service.best.customer.in.half.year}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BestCustomerDto getBestCustomerInHalfYear() {
        BestCustomerDto bestCustomerDto = new BestCustomerDto();
        String[][] weeklyPurchases = purchaseRepository.findBestCustomerInHalfYear();
        if(weeklyPurchases.length > 0) {
            bestCustomerDto.setBuyersId(Long.parseLong(weeklyPurchases[0][0]));
            bestCustomerDto.setCount(Integer.parseInt(weeklyPurchases[0][1]));
            return bestCustomerDto;
        } else {
            bestCustomerDto.setBuyersId(0);
            bestCustomerDto.setCount(0);
            return bestCustomerDto;
        }

    }

}
