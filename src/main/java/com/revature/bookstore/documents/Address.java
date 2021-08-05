package com.revature.bookstore.documents;

import java.util.Objects;

public class Address {

    private String unitNumber;
    private String streetName;
    private String city;
    private String state;
    private String postalCode;

    public Address(String unitNumber, String streetName, String city, String state, String postalCode) {
        this.unitNumber = unitNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(unitNumber, address.unitNumber)
                && Objects.equals(streetName, address.streetName)
                && Objects.equals(city, address.city)
                && Objects.equals(state, address.state)
                && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitNumber, streetName, city, state, postalCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "unitNumber='" + unitNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

}
