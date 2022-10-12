package com.devmountain.noteApp.controllers;

import com.devmountain.noteApp.dtos.UserDto;
import com.devmountain.noteApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    // dependencies
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto) {
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }
}



/*
Part 3: Step 3 - Creating Controllers
-- use @RestController
-- use @RequestMapping - can accept a string as an option to designate a class level path where all endpoints
    will begin.

Process?
1. Must use Autowire the dependencies that the controllers need
    a: UserService - controller need to interact with the ServiceLayer
    b: PasswordEncoder - incoming passwords

2. Post request - register a user
    a: public method that returns List<String> called addUser and accepts an arg of type UserDto called userDto
    b: @PostMapping with endpoint "/register"
    c: @ResponseBody the UserDto - maps the JSON obj on the req to the DTO and makes it usable
    d: hash the incoming password using encode() - keeps raw pwd safe
    e: invoke setPassword() on the userDto arg and pass in the "passHash"
    f: return addUser() on the userService dependency and pass in "userDto" as the arg

3. Post request - login a user
    a: public method that returns List<String> called useLogin and accepts an arg of type UserDto called userDto
    b: @PostMapping with endpoint "/login"
    c: @ResponseBody the UserDto - maps the JSON obj on the req to the DTO and makes it usable
    d: return userLogin() on the userService dependency and pass in "userDto" as the arg
 */