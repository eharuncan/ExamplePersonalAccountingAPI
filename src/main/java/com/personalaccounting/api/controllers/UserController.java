package com.personalaccounting.api.controllers;

import java.util.List;

import com.personalaccounting.api.domain.User;
import com.personalaccounting.api.dtos.UserEditDto;
import com.personalaccounting.api.dtos.UserLoginDto;
import com.personalaccounting.api.dtos.UserRegisterDto;
import com.personalaccounting.api.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.personalaccounting.api.utils.Utils.apiURL;

@RestController
class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(apiURL + "/users")
    List<User> all() {
        return userService.getUsers();
    }

    @GetMapping(apiURL + "/users/{id}")
    User one(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(apiURL + "/register")
    User newUser(@RequestBody UserRegisterDto newUser) {
        return userService.register(newUser);
    }

    @PutMapping(apiURL + "/users/{id}")
    UserEditDto replaceUser(@RequestBody UserEditDto newUserDto, @PathVariable Long id) {
        return userService.editUser(newUserDto, id);
    }

    @DeleteMapping(apiURL + "/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping(apiURL + "/login")
    User loginUser(@RequestBody UserLoginDto user) {
        return userService.login(user);
    }

    @PostMapping(apiURL + "/logout")
    void logoutUser(@RequestBody User user) {
        userService.logout(user);
    }
}