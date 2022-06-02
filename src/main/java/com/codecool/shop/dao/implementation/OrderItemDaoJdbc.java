package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderItemDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderItem;
import com.codecool.shop.model.Product;

import java.util.List;

public class OrderItemDaoJdbc implements OrderItemDao {
    @Override
    public void add(OrderItem item) {

    }

    @Override
    public OrderItem find(int id, Product product) {
        return null;
    }

    @Override
    public void remove(int id, Product product) {

    }

    @Override
    public List<OrderItem> getAllByOrder(Order order) {
        return null;
    }
}
