package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BestCustomerDto {

    @Schema(description = "unique identifier of a Best Customer.",
            example = "1", required = true)
    private long buyersId;

    @Schema(description = "The number of purchases made by the buyer.",
            example = "1", required = true)
    private int count;
}
