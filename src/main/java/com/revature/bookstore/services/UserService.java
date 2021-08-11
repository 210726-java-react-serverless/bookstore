package com.revature.bookstore.services;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.datasource.repos.UserRepository;
import com.revature.bookstore.util.PasswordUtils;
import com.revature.bookstore.util.exceptions.AuthenticationException;
import com.revature.bookstore.util.exceptions.InvalidRequestException;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class UserService {

    private final UserRepository userRepo;
    private final PasswordUtils passwordUtils;

    public UserService(UserRepository userRepo, PasswordUtils passwordUtils) {
        this.userRepo = userRepo;
        this.passwordUtils = passwordUtils;
    }

    // TODO: make this actually talk to the datasource via the userRepo
    public List<AppUser> findAll() {
        AppUser user1 = new AppUser("a", "a", "a", "a", "a");
        AppUser user2 = new AppUser("b", "b", "b", "b", "b");
        AppUser user3 = new AppUser("c", "c", "c", "c", "c");
        AppUser user4 = new AppUser("d", "d", "d", "d", "d");

        return Arrays.asList(user1, user2, user3, user4);
    }

    // TODO: make this actually talk to the datasource via the userRepo
    public AppUser findUserById(String id) {
        return new AppUser("d", "d", "d", "d", "d");
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

        newUser.setRegistrationDateTime(LocalDateTime.now());
        String encryptedPassword = passwordUtils.generateSecurePassword(newUser.getPassword());
        newUser.setPassword(encryptedPassword);

        return userRepo.save(newUser);

    }

    public AppUser login(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid user credentials provided!");
        }

        String encryptedPassword = passwordUtils.generateSecurePassword(password);
        AppUser authUser = userRepo.findUserByCredentials(username, encryptedPassword);

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
