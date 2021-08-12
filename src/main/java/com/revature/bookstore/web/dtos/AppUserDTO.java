package com.revature.bookstore.web.dtos;

import com.revature.bookstore.datasource.documents.Address;
import com.revature.bookstore.datasource.documents.AppUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class AppUserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Address address;
    private String registrationDateTime;

    public AppUserDTO(AppUser subject) {
        this.id = subject.getId();
        this.firstName = subject.getFirstName();
        this.lastName = subject.getLastName();
        this.email = subject.getEmail();
        this.username = subject.getUsername();
        this.address = subject.getAddress();
        this.registrationDateTime = subject.getRegistrationDateTime().toString();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(String registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserDTO that = (AppUserDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(username, that.username) && Objects.equals(address, that.address) && Objects.equals(registrationDateTime, that.registrationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, username, address, registrationDateTime);
    }

    @Override
    public String toString() {
        return "AppUserDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", address=" + address +
                ", registrationDateTime='" + registrationDateTime + '\'' +
                '}';
    }

}
