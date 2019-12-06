package shopping.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_PRODUCT")
@Data
@EqualsAndHashCode
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PRODUCT_ID", updatable = false, nullable = false)
    private long productId;

    @Column(name = "PRODUCT_TITLE")
    private String productTitle;

    @Column(name = "PRODUCT_PRICE")
    private BigDecimal productPrice;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Purchase> purchaseList = new ArrayList<>(0);

}
