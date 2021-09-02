package com.revature.bookstore.services;

import com.revature.bookstore.datasource.documents.AppUser;
import com.revature.bookstore.datasource.repos.UserRepository;
import com.revature.bookstore.util.PasswordUtils;
import com.revature.bookstore.util.exceptions.InvalidRequestException;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserServiceTestSuite {

    UserService sut;

    private PasswordUtils mockPasswordUtils;
    private UserRepository mockUserRepo;

    @BeforeEach
    public void beforeEachTest() {
        mockPasswordUtils = mock(PasswordUtils.class);
        mockUserRepo = mock(UserRepository.class);
        sut = new UserService(mockUserRepo, mockPasswordUtils);
    }

    @AfterEach
    public void afterEachTest() {
        sut = null;
    }

    @Test
    public void isUserValid_returnsTrue_givenValidUser() {

        // Arrange
        AppUser validUser = new AppUser("valid", "valid", "valid", "valid", "valid");

        // Act
        boolean actualResult = sut.isUserValid(validUser);

        // Assert
        assertTrue(actualResult);

    }

    @Test
    public void isUserValid_returnsFalse_givenUserWithNullOrEmptyFirstName() {

        // Arrange
        AppUser invalidUser1 = new AppUser(null, "valid", "valid", "valid", "valid");
        AppUser invalidUser2 = new AppUser("", "valid", "valid", "valid", "valid");
        AppUser invalidUser3 = new AppUser("        ", "valid", "valid", "valid", "valid");

        // Act
        boolean actualResult1 = sut.isUserValid(invalidUser1);
        boolean actualResult2 = sut.isUserValid(invalidUser2);
        boolean actualResult3 = sut.isUserValid(invalidUser3);

        // Assert
        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);

    }

    @Test
    public void register_returnsSuccessfully_whenGivenValidUser() {

        // Arrange
        AppUser expectedResult = new AppUser("valid", "valid", "valid", "valid", "valid");
        AppUser validUser = new AppUser("valid", "valid", "valid", "valid", "valid");
        when(mockUserRepo.save(any())).thenReturn(expectedResult);
        when(mockPasswordUtils.generateSecurePassword(anyString())).thenReturn("encrypted");

        // Act
        AppUser actualResult = sut.register(validUser);

        // Assert
        assertEquals(expectedResult, actualResult);
        assertNotEquals(expectedResult.getPassword(), actualResult.getPassword());
        verify(mockUserRepo, times(1)).save(any());
        verify(mockPasswordUtils, times(1)).generateSecurePassword(anyString());

    }

    @Test
    public void register_throwsException_whenGivenInvalidUser() {

        // Arrange
        AppUser invalidUser = new AppUser(null, "", "", "", "");

        // Act
        InvalidRequestException e = assertThrows(InvalidRequestException.class, () -> sut.register(invalidUser));

        // Assert
        assertEquals("Invalid user data provided!", e.getMessage());
        verify(mockUserRepo, times(0)).save(any());

    }

    @Test
    public void register_throwsException_whenGivenUserWithDuplicateUsername() {

        // Arrange
        AppUser existingUser = new AppUser("original", "original", "original", "duplicate", "original");
        AppUser duplicate = new AppUser("first", "last", "email", "duplicate", "password");
        when(mockUserRepo.findAppUserByUsername(duplicate.getUsername())).thenReturn(existingUser);

        // Act
        ResourcePersistenceException e = assertThrows(ResourcePersistenceException.class, () -> sut.register(duplicate));

        // Assert
        assertEquals("Provided username is already taken!", e.getMessage());
        verify(mockUserRepo, times(1)).findAppUserByUsername(duplicate.getUsername());
        verify(mockUserRepo, times(0)).save(duplicate);

    }

    @Test
    public void register_throwsException_whenGivenUserWithDuplicateEmail() {

        // Arrange
        AppUser existingUser = new AppUser("original", "original", "duplicate", "original", "original");
        AppUser duplicate = new AppUser("first", "last", "duplicate", "username", "password");
        when(mockUserRepo.findAppUserByEmail(duplicate.getEmail())).thenReturn(existingUser);

        // Act
        ResourcePersistenceException e = assertThrows(ResourcePersistenceException.class, () -> sut.register(duplicate));

        // Assert
        assertEquals("Provided email is already taken!", e.getMessage());
        verify(mockUserRepo, times(1)).findAppUserByEmail(duplicate.getEmail());
        verify(mockUserRepo, times(0)).save(duplicate);

    }

}
