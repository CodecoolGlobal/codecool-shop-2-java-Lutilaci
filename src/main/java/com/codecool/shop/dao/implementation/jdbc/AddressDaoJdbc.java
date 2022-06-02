package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.model.Address;

import javax.sql.DataSource;
import java.sql.*;

public class AddressDaoJdbc implements AddressDao {

    private DataSource dataSource;

    public AddressDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Address address) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO address (first_name, last_name, street, city, zipcode, user_id) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, address.getFirstName());
            statement.setString(2, address.getLastName());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getZipCode());
            statement.setInt(5, address.getUserId());
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            address.setId(resultSet.getInt(1));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }
}
