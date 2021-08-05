package com.revature.bookstore.documents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.awt.image.TileObserver;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppUser {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Address address;
    private LocalDateTime registrationTime;

    public AppUser() {
        super();
    }

    public AppUser(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public AppUser(String firstName, String lastName, String email, String username, String password, Address address) {
        this(firstName, lastName, email, username, password);
        this.address = address;
    }

    public AppUser(String firstName, String lastName, String email, String username, String password, LocalDateTime registrationTime) {
        this(firstName, lastName, email, username, password);
        this.registrationTime = registrationTime;
    }

    public AppUser(String firstName, String lastName, String email, String username, String password, Address address, LocalDateTime registrationTime) {
        this(firstName, lastName, email, username, password, address);
        this.registrationTime = registrationTime;
    }

    public AppUser(String id, String firstName, String lastName, String email, String username, String password, Address address, LocalDateTime registrationTime) {
        this(firstName, lastName, email, username, password, address, registrationTime);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(id, appUser.id) && Objects.equals(firstName, appUser.firstName) && Objects.equals(lastName, appUser.lastName) && Objects.equals(email, appUser.email) && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(address, appUser.address) && Objects.equals(registrationTime, appUser.registrationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, username, password, address, registrationTime);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", registrationTime=" + registrationTime +
                '}';
    }

}
