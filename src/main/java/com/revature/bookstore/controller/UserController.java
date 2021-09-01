package com.revature.bookstore.controller;


import com.revature.bookstore.dtos.AppUserDTO;
import com.revature.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping(value = "/json", produces = "application/json")
    public boolean emailIsAvailable(){
        return userService.isEmailAvailable("jose@tejada.com");
    }

    @GetMapping(value = "/json", produces = "application/json")
    public List<AppUserDTO> findAll() {
        return userService.findAll();
    }


}
