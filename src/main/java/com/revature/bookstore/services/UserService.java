package com.revature.bookstore.services;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.datasource.repos.UserRepository;
import com.revature.bookstore.util.PasswordUtils;
import com.revature.bookstore.util.exceptions.AuthenticationException;
import com.revature.bookstore.util.exceptions.InvalidRequestException;
import com.revature.bookstore.util.exceptions.ResourceNotFoundException;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;
import com.revature.bookstore.web.dtos.AppUserDTO;
import com.revature.bookstore.web.dtos.AvailabilityStatus;
import com.revature.bookstore.web.dtos.Principal;
import com.revature.bookstore.web.intercom.AuthServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordUtils passwordUtils;
    private final AuthServiceClient authClient;

    @Autowired
    public UserService(UserRepository userRepo, PasswordUtils passwordUtils, AuthServiceClient authClient) {
        this.userRepo = userRepo;
        this.passwordUtils = passwordUtils;
        this.authClient = authClient;
    }

    public List<AppUserDTO> findAll() {
        System.out.println(userRepo.findAll());
        return userRepo.findAll()
                       .stream()
                       .map(AppUserDTO::new)
                       .collect(Collectors.toList());
    }

    public AppUserDTO findUserById(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new InvalidRequestException("Invalid id provided");
        }

        return userRepo.findById(id)
                       .map(AppUserDTO::new)
                       .orElseThrow(ResourceNotFoundException::new);


    }

    public AppUser register(AppUser newUser) {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user data provided!");
        }

        if (userRepo.findAppUserByUsername(newUser.getUsername()) != null) {
            throw new ResourcePersistenceException("Provided username is already taken!");
        }

        if (userRepo.findAppUserByEmail(newUser.getEmail()) != null) {
            throw new ResourcePersistenceException("Provided email is already taken!");
        }

        newUser.setRegistrationDateTime(LocalDateTime.now());
        String encryptedPassword = passwordUtils.generateSecurePassword(newUser.getPassword());
        newUser.setPassword(encryptedPassword);

        return userRepo.save(newUser);

    }

    public Principal login(String username, String password) {

        String encryptedPassword = passwordUtils.generateSecurePassword(password);
        AppUser authUser = userRepo.findAppUserByUsernameAndPassword(username, encryptedPassword);

        if (authUser == null) {
            throw new AuthenticationException("Invalid credentials provided!");
        }

        Principal principal = new Principal(authUser);
        String token = authClient.generateTokenFromPrincipal(principal);
        principal.setToken(token);

        return principal;

    }

    public AvailabilityStatus determineAvailability(String field, String value) {
        if (field.equalsIgnoreCase("username")) {
            return new AvailabilityStatus(isUsernameAvailable(value));
        } else if (field.equalsIgnoreCase("email")) {
            return new AvailabilityStatus(isEmailAvailable(value));
        } else {
            throw new InvalidRequestException("No availability status for provided field: " + field);
        }
    }

    public boolean isUsernameAvailable(String username) {

        if (username == null || username.trim().equals("")) {
            throw new InvalidRequestException("Invalid email value provided!");
        }

        return (userRepo.findAppUserByUsername(username) == null);
    }

    public boolean isEmailAvailable(String email) {

        if (email == null || email.trim().equals("")) {
            throw new InvalidRequestException("Invalid email value provided!");
        }

        return (userRepo.findAppUserByEmail(email) == null);
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
