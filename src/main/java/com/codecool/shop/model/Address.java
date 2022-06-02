package com.codecool.shop.model;

import java.util.List;

public class Address {

    // Data from shipping
    public int id;
    public String firstName;
    public String lastName;
    public String street;
    public String city;
    public String zipCode;

    public List<Product> productList;

    public Address(String firstName, String lastName, String email, String street, String city, String zipCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }



}
