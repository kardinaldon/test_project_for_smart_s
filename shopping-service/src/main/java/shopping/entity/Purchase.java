package shopping.entity;

import shopping.domain.LocalDateAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "APP_PURCHASE")
@Data
@EqualsAndHashCode
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PURCHASE_ID", updatable = false, nullable = false)
    private long purchaseId;

    @Column(name="PURCHASE_ITEM")
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY,
            mappedBy = "person")
    private List<Product> purchaseItem;

    @Column(name="COUNT_OF_PRODUCTS")
    private int countOfProducts;

    @Column(name="PURCHASE_AMOUNT")
    private BigDecimal purchaseAmount;

    @Column(name="BUYERS_NAME")
    private String buyersName;

    @Column(name="BUYERS_LAST_NAME")
    private String buyersLastName;

    @Column(name="BUYERS_AGE")
    private int buyersAge;

    @Column(name="PURCHASE_DATE")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate purchaseDate;
}
