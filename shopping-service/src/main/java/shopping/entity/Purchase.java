package shopping.entity;

import adapter.LocalDateAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "APP_PURCHASE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Purchase implements Serializable {


    @Schema(description = "purchase id.",
            example = "2", required = false)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PURCHASE_ID")
    private long purchaseId;

    @Schema(description = "purchase name.",
            example = "Smartphone", required = true)
    @Column(name="PURCHASE_NAME")
    private String purchaseName;

    @Schema(description = "quantity of goods purchased.",
            example = "3", required = true)
    @Column(name="COUNT_OF_PURCHASE")
    private int countOfPurchase;

    @Schema(description = "purchase amount.",
            example = "1000", required = true)
    @Column(name="PURCHASE_AMOUNT")
    private BigDecimal purchaseAmount;

    @Schema(description = "customer id.",
            example = "2", required = true)
    @Column(name="BUYERS_ID")
    private int buyersId;

    @Schema(description = "date of purchase.",
            example = "02.05.2019", required = true)
    @Column(name="PURCHASE_DATE")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate purchaseDate;

}
