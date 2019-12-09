package user.controller;

import dto.UserDto;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import user.repository.UsersRepository;
import user.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${user.service.prefix}")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UsersRepository usersRepository;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@RequestParam long id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<User> userOptional = usersRepository.findById(id);
        User user = userOptional.get();
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @GetMapping(value = "${user.service.user.by.age.prefix}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsersByAge(@RequestParam int age) {
        ModelMapper modelMapper = new ModelMapper();
        List<UserDto> userDto = new ArrayList<>();
        List<User> userList = usersRepository.findByAge(age);
        for (User user : userList) {
            userDto.add(modelMapper.map(user,UserDto.class));
        }
        return userDto;
    }

}
