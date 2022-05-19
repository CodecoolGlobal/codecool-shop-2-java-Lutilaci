package com.codecool.shop.model;

public class OrderDetails {

    // Data from shipping
    String firstName;
    String lastName;
    String email;
    String street;
    String city;
    String zipCode;



    public OrderDetails(String firstName, String lastName, String email, String street, String city, String zipCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

}
