package com.revature.bookstore.services;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.datasource.repos.UserRepository;
import com.revature.bookstore.util.exceptions.AuthenticationException;
import com.revature.bookstore.util.exceptions.InvalidRequestException;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;

public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public AppUser register(AppUser newUser) {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user data provided!");
        }

        if (userRepo.findUserByUsername(newUser.getUsername()) != null) {
            throw new ResourcePersistenceException("Provided username is already taken!");
        }

        if (userRepo.findUserByEmail(newUser.getEmail()) != null) {
            throw new ResourcePersistenceException("Provided username is already taken!");
        }

        return userRepo.save(newUser);

    }

    public AppUser login(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid user credentials provided!");
        }

        AppUser authUser = userRepo.findUserByCredentials(username, password);

        if (authUser == null) {
            throw new AuthenticationException("Invalid credentials provided!");
        }

        return authUser;

    }

    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return user.getPassword() != null && !user.getPassword().trim().equals("");
    }

}
