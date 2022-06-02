package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name FROM suppliers WHERE id = (?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            Supplier supplier = null;
            if (rs.next()) {
                supplier = new Supplier(rs.getString(1));
            }
            return supplier;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT name FROM suppliers";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<Supplier> supplierList = new ArrayList<>();
            while (rs.next()){
                Supplier supCat = new Supplier(rs.getString(1));
                supplierList.add(supCat);
            }
            return supplierList;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading supplier with id: ", e);
        }
    }
}
