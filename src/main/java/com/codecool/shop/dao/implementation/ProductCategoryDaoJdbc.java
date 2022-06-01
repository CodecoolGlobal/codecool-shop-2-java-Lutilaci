package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT name, description FROM product_categories";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<ProductCategory> prodCatList = new ArrayList<>();
            while (rs.next()){
                ProductCategory productCat = new ProductCategory(rs.getString(1), rs.getString(2));
                prodCatList.add(productCat);
            }
            return prodCatList;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading category with id: ", e);
        }
    }
}
