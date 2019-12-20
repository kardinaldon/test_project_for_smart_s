package front.controller;


import constant.PathConstants;
import front.entity.Credentials;
import front.repository.CredentialsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Controller
@RequestMapping(value = PathConstants.FRONTEND_AUTH)
@AllArgsConstructor
@Tag(name = "Authentication Controller", description = "the controller is used as an access point in browser gui, when connected to it with the correct data (username and password base64), the cookie is set to \"id\" (in the future this is used to display the purchases of this user)")
public class AuthenticationController {

    @Autowired
    private final CredentialsRepository credentialRepository;

    @Operation(summary = "Authentication", description = "with correct access, the token containing the username and password of the user is decrypted, then it searches the credentials table, in the database, \"bayerId\" is extracted from this record and set as a cookie \"id\" (session scope)", tags = {"Authentication Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged in correctly"),
            @ApiResponse(responseCode = "400", description = "bad request")})
    @GetMapping(value = PathConstants.FRONTEND_AUTH_API)
    public ResponseEntity homePage(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        byte[] data = Base64.getDecoder().decode(auth.substring(6));
        String loginAndPass = new String(data);
        String login = loginAndPass.substring(0, loginAndPass.indexOf(":"));
        String pass = loginAndPass.substring(loginAndPass.indexOf(":") + 1);
        System.out.println(new BCryptPasswordEncoder().encode(pass));
        try {
            Credentials credentials = credentialRepository.findByUserName(login);
            HttpCookie cookie = ResponseCookie.from("id", String.valueOf(credentials.getUserId())).path("/").build();
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(null);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }
}
