package com.revature.bookstore.services;

import com.revature.bookstore.documents.AppUser;
import com.revature.bookstore.repos.UserRepository;
import com.revature.bookstore.util.UserSession;
import com.revature.bookstore.util.exceptions.InvalidRequestException;
import com.revature.bookstore.util.exceptions.ResourcePersistenceException;
import org.junit.*;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTestSuite {

    UserService sut;

    private UserSession mockUserSession;
    private UserRepository mockUserRepo;

    @Before
    public void beforeEachTest() {
        mockUserSession = mock(UserSession.class);
        mockUserRepo = mock(UserRepository.class);
        sut = new UserService(mockUserRepo, mockUserSession);
    }

    @After
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
        Assert.assertTrue("Expected user to be considered valid!", actualResult);

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
        Assert.assertFalse("User first name cannot be null!", actualResult1);
        Assert.assertFalse("User first name cannot be an empty string!", actualResult2);
        Assert.assertFalse("User first name cannot be only whitespace!", actualResult3);

    }

    @Test
    public void register_returnsSuccessfully_whenGivenValidUser() {

        // Arrange
        AppUser expectedResult = new AppUser("valid", "valid", "valid", "valid", "valid");
        AppUser validUser = new AppUser("valid", "valid", "valid", "valid", "valid");
        when(mockUserRepo.save(any())).thenReturn(expectedResult);

        // Act
        AppUser actualResult = sut.register(validUser);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
        verify(mockUserRepo, times(2)).save(any());

    }

    @Test(expected = InvalidRequestException.class)
    public void register_throwsException_whenGivenInvalidUser() {

        // Arrange
        AppUser invalidUser = new AppUser(null, "", "", "", "");

        // Act
        try {
            sut.register(invalidUser);
        } finally {
            // Assert
            verify(mockUserRepo, times(0)).save(any());
        }

    }

    @Test(expected = ResourcePersistenceException.class)
    public void register_throwsException_whenGivenUserWithDuplicateUsername() {

        // Arrange
        AppUser existingUser = new AppUser("original", "original", "original", "duplicate", "original");
        AppUser duplicate = new AppUser("first", "last", "email", "duplicate", "password");
        when(mockUserRepo.findUserByUsername(duplicate.getUsername())).thenReturn(existingUser);

        // Act
        try {
            sut.register(duplicate);
        } finally {
            // Assert
            verify(mockUserRepo, times(1)).findUserByUsername(duplicate.getUsername());
            verify(mockUserRepo, times(0)).save(duplicate);
        }

    }

}
