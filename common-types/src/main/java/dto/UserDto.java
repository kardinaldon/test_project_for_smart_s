package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
public class UserDto {

    @Schema(description = "user ID.",
            example = "1", required = false)
    private long userId;

    @Schema(description = "user name.",
            example = "Donald", required = true)
    private String userName;

    @Schema(description = "user last name.",
            example = "Ivanov", required = true)
    private String userLastName;

    @Schema(description = "user age.",
            example = "18", required = true)
    private int userAge;
}
