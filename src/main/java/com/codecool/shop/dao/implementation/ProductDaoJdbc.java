package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private DataSource dataSource;
    private ProductCategoryDao prodCatDaoJdbc;
    private SupplierDao supplierDaoJdbc;

    public ProductDaoJdbc(DataSource dataSource, ProductCategoryDao prodCatDaoJdbc, SupplierDao supplierDaoJdbc) {
        this.dataSource = dataSource;
        this.prodCatDaoJdbc = prodCatDaoJdbc;
        this.supplierDaoJdbc = supplierDaoJdbc;
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO products (prod_name, description, category_id, unit_price, currency, supplier_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            //statement.setString(2, product.);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT prod_name, unit_price, currency, description, category_id, supplier_id, id FROM products";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List <Product> products = new ArrayList<>();
            while (rs.next()) {
                ProductCategory productCategory = prodCatDaoJdbc.find(rs.getInt(5));
                Supplier supplier = supplierDaoJdbc.find(rs.getInt(6));
                        Product product = new Product(rs.getString(1), rs.getBigDecimal(2), rs.getString(3), rs.getString(4), productCategory, supplier);
                        product.setId(rs.getInt(7));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
