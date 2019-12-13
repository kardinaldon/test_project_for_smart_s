package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CredentialsDto {

    @Schema(description = "current user login.",
            example = "user", required = true)
    private String login;

    @Schema(description = "current user password in sha254.",
            example = "user", required = true)
    private String password;

}
