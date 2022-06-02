package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.util.List;

public interface OrderDao {

    void add(Order order);
    Order find(int id);
    void remove(int id);

    List<Order> getAll();
}
