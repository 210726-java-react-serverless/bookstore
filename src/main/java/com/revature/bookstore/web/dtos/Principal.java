package com.revature.bookstore.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.bookstore.datasource.documents.AppUser;
import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data @NoArgsConstructor
public class Principal {

    private String id;
    private String username;

    @JsonIgnore
    private String token;

    public Principal(AppUser subject) {
        this.id = subject.getId();
        this.username = subject.getUsername();
    }

}
