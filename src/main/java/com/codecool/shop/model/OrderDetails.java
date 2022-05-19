package com.codecool.shop.model;

import java.util.List;

public class OrderDetails {

    // Data from shipping
    String firstName;
    String lastName;
    String email;
    String street;
    String city;
    String zipCode;

    List<Product> productList;

    public OrderDetails(String firstName, String lastName, String email, String street, String city, String zipCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }



}
