package com.revature.bookstore.web.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class Credentials {

    @NotBlank(message = "Username cannot be null or blank")
    private String username;

    @NotBlank(message = "Password cannot be null or blank")
    private String password;


}
