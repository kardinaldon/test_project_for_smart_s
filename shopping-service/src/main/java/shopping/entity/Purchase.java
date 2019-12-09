package shopping.entity;

import adapter.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Array;
import java.time.LocalDate;

@Entity
@Table(name = "APP_PURCHASE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Purchase implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PURCHASE_ID")
    private long purchaseId;

    @Column(name="PURCHASE_NAME")
    private String purchaseName;

    @Column(name="COUNT_OF_PURCHASE")
    private int countOfPurchas;

    @Column(name="PURCHASE_AMOUNT")
    private BigDecimal purchaseAmount;

    @Column(name="BUYERS_ID")
    private int buyersId;

    @Column(name="PURCHASE_DATE")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate purchaseDate;

}
