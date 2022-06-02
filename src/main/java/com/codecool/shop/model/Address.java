package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Address {

    // Data from shipping
    public int id;

    private final int userId;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zipCode;
    private List<Product> productList;

    public Address(int userId, String firstName, String lastName, String street, String city, String zipCode){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        productList = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProductToProductList(Product product){
        productList.add(product);
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
