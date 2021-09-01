package com.revature.bookstore.contollers;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.services.UserService;
import com.revature.bookstore.web.dtos.AppUserDTO;
import com.revature.bookstore.web.dtos.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public List<AppUserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping(produces = "application/json")
    public AppUserDTO findUserbyID(@RequestParam String id) {
        return userService.findUserById(id);
    }

    @PostMapping(value = "/register", produces = "application/json")
    public Principal register(@RequestBody AppUser newUser) {
        return new Principal(userService.register(newUser));
    }
}
