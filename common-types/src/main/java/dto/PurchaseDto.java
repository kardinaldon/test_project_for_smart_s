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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@XmlRootElement(name = "Purchase")
public class PurchaseDto {

    @Schema(description = "purchase id.",
            example = "2", required = false)
    @XmlElement (name = "purchaseId", type = Long.class)
    private long purchaseId;

    @Schema(description = "purchase name.",
            example = "Smartphone", required = true)
    @XmlElement (name = "purchaseName", type = String.class)
    private String purchaseName;

    @Schema(description = "quantity of goods purchased.",
            example = "3", required = true)
    @XmlElement (name = "countOfPurchase", type = Integer.class)
    private int countOfPurchase;

    @Schema(description = "purchase amount.",
            example = "1000", required = true)
    @XmlElement (name = "purchaseAmount", type = BigDecimal.class)
    private BigDecimal purchaseAmount;

    @Schema(description = "customer id.",
            example = "2", required = true)
    @XmlElement (name = "buyersId", type = Integer.class)
    private int buyersId;


    @Schema(description = "date of purchase.",
            example = "02.05.2019", required = true)
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate purchaseDate;
}
