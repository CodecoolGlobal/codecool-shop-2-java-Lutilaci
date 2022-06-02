package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    private DataSource dataSource;
    public OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Order order) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id FROM users WHERE email = (?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
