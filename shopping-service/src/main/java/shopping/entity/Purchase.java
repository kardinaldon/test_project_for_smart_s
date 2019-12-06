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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_PURCHASE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Purchase implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PURCHASE_ID", updatable = false, nullable = false)
    private long purchaseId;

    @ManyToMany(mappedBy = "purchaseList",fetch = FetchType.EAGER)
    private List<Product> purchaseItem = new ArrayList<>(0);

    @Column(name="COUNT_OF_PRODUCTS")
    private int countOfProducts;

    @Column(name="PURCHASE_AMOUNT")
    private BigDecimal purchaseAmount;

    @Column(name="BUYERS_ID")
    private int buyersId;

    @Column(name="PURCHASE_DATE")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate purchaseDate;

}
