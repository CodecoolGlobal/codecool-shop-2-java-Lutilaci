package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private int id;
    private int userId;
    private Address address;
    private double total_price;
    private Date time;

    private List<OrderItem> content;

    public Order(int userId, Address address, double total_price) {
        this.userId = userId;
        this.address = address;
        this.total_price = total_price;
        this.time = new Date();
        content = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getUser() {
        return userId;
    }

    public Address getAddress() {
        return address;
    }

    public double getTotal_price() {
        return total_price;
    }

    public Date getTime() {
        return time;
    }

    public List<OrderItem> getContent() {
        return content;
    }

    public void addOrderItem(OrderItem orderItem){
        content.add(orderItem);
    }
}
