package dto;

import adapter.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PurchaseDto {

    private long purchaseId;
    private String purchaseName;
    private int countOfPurchas;
    private BigDecimal purchaseAmount;
    private int buyersId;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate purchaseDate;
}
