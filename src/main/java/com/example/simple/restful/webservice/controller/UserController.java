package com.example.simple.restful.webservice.controller;

import com.example.simple.restful.webservice.method.User;
import com.example.simple.restful.webservice.method.UserCriteria;
import com.example.simple.restful.webservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable UUID id,
                           @RequestParam Integer age) {
        userService.updateUser(id, age);
    }

    @GetMapping
    public List<User> filtering(UserCriteria userCriteria) {
        return userService.filteringByAge(userCriteria);
    }

}