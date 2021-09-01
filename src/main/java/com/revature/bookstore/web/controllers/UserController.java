package com.revature.bookstore.web.controllers;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.web.dtos.AppUserDTO;
import com.revature.bookstore.web.dtos.AvailabilityStatus;
import com.revature.bookstore.web.dtos.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public List<AppUserDTO> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public AppUserDTO getUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @GetMapping("/availability")
    public AvailabilityStatus checkAvailability(@RequestParam String field, @RequestParam String value) {
        return userService.determineAvailability(field, value);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Principal registerNewUser(@RequestBody AppUser newUser) {
        return new Principal(userService.register(newUser));
    }

}
