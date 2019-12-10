package user.controller;

import constant.PathConstants;
import dto.UserDto;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import user.repository.UsersRepository;
import user.entity.User;

import java.util.Optional;

@RestController
@RequestMapping(PathConstants.USER_SERVICE_PREFIX)
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UsersRepository usersRepository;

    @GetMapping(value = PathConstants.USER_SERVICE_BY_ID,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@RequestParam long id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<User> userOptional = usersRepository.findById(id);
        User user = userOptional.get();
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }

}
