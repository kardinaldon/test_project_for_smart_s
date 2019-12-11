package dto;

import adapter.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "purchase id.",
            example = "2", required = false)
    private long purchaseId;

    @Schema(description = "purchase name.",
            example = "Smartphone", required = true)
    private String purchaseName;

    @Schema(description = "quantity of goods purchased.",
            example = "3", required = true)
    private int countOfPurchase;

    @Schema(description = "purchase amount.",
            example = "1000", required = true)
    private BigDecimal purchaseAmount;

    @Schema(description = "customer id.",
            example = "2", required = true)
    private int buyersId;

    @Schema(description = "date of purchase.",
            example = "02.05.2019", required = true)
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate purchaseDate;
}
