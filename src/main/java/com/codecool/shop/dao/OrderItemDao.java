package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.util.List;

public interface OrderItemDao {

    void add(OrderItem item);
    OrderItem find(int id, Product product);
    void remove(int id, Product product);

    List<OrderItem> getAllByOrder(Order order);
}
