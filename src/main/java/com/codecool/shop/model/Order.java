package com.codecool.shop.model;

import java.util.Date;
import java.util.List;

public class Order {

    private int id;
    private User user;
    private Address address;
    private double total_price;
    private Date time;
    private List<OrderItem> content;

    public int getId() {
        return id;
    }
}
