package com.codecool.shop.model;

public class OrderItem {

    private Product product;
    private int amount;
    private int order_id;
    private double subtotal_price;

    public OrderItem(Product product, int amount, Order order) {
        this.product = product;
        this.amount = amount;
        this.order_id = order.getId();
    }
}
