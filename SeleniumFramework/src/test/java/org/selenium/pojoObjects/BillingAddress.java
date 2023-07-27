package org.selenium.pojoObjects;

public class BillingAddress {
    private String firstName;
    private String lastName;
    private String country;
    private String addressOne;
    private String city;
    private String state;
    private String zipCode;
    private String email;

    public BillingAddress(String firstName, String lastName, String country, String addressOne,
                          String city, String state, String zipCode, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.addressOne = addressOne;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;

    }
    public BillingAddress() {

    }

    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public BillingAddress setAddressOne(String addressOne) {
        this.addressOne = addressOne;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public BillingAddress setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public BillingAddress setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getState() {
        return state;
    }

    public BillingAddress setState(String state) {
        this.state = state;
        return this;
    }
}
