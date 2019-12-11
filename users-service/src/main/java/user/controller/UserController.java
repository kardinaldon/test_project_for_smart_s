package user.controller;

import constant.PathConstants;
import dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.repository.UsersRepository;
import user.entity.User;

import java.util.Optional;

@RestController
@RequestMapping(PathConstants.USER_SERVICE_PREFIX)
@AllArgsConstructor
@Tag(name = "User controller", description = "user service API")
public class UserController {

    @Autowired
    private final UsersRepository usersRepository;

    @Operation(summary = "Find user by ID", description = "user by ID", tags = {"User controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "user not found")})
    @GetMapping(value = PathConstants.USER_SERVICE_BY_ID,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getUserById(@RequestParam long id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Optional<User> userOptional = usersRepository.findById(id);
            User user = userOptional.get();
            UserDto userDto = modelMapper.map(user,UserDto.class);
            return new ResponseEntity(userDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
