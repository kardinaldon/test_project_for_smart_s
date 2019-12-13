package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MostPurchasedItemDto {

    @Schema(description = "purchase name.",
            example = "Smartphone", required = true)
    private String purchaseName;

    @Schema(description = "the count of purchases of this product.",
            example = "2", required = true)
    private int count;
}
