
package dto;

import adapter.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement(name = "PurchaseDto")
@XmlType(propOrder = {"purchaseName", "countOfPurchase", "purchaseAmount", "buyersId", "purchaseDate", "purchaseId"})
@Data
public class PurchaseDto {

    @Schema(description = "purchase id.",
            example = "2", required = false)
    @XmlTransient
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate purchaseDate;

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public int getCountOfPurchase() {
        return countOfPurchase;
    }

    public void setCountOfPurchase(int countOfPurchase) {
        this.countOfPurchase = countOfPurchase;
    }

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(int buyersId) {
        this.buyersId = buyersId;
    }

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

//    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseDto that = (PurchaseDto) o;

        if (purchaseId != that.purchaseId) return false;
        if (countOfPurchase != that.countOfPurchase) return false;
        if (buyersId != that.buyersId) return false;
        if (purchaseName != null ? !purchaseName.equals(that.purchaseName) : that.purchaseName != null) return false;
        if (purchaseAmount != null ? !purchaseAmount.equals(that.purchaseAmount) : that.purchaseAmount != null)
            return false;
        return purchaseDate != null ? purchaseDate.equals(that.purchaseDate) : that.purchaseDate == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (purchaseId ^ (purchaseId >>> 32));
        result = 31 * result + (purchaseName != null ? purchaseName.hashCode() : 0);
        result = 31 * result + countOfPurchase;
        result = 31 * result + (purchaseAmount != null ? purchaseAmount.hashCode() : 0);
        result = 31 * result + buyersId;
        result = 31 * result + (purchaseDate != null ? purchaseDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PurchaseDto{" +
                "purchaseId=" + purchaseId +
                ", purchaseName='" + purchaseName + '\'' +
                ", countOfPurchase=" + countOfPurchase +
                ", purchaseAmount=" + purchaseAmount +
                ", buyersId=" + buyersId +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
