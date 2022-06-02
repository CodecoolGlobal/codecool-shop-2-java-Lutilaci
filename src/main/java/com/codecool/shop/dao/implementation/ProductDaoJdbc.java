package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
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
                ProductCategory productCategory = getCategoryById(rs.getInt(5));
                Supplier supplier = getSupplierById(rs.getInt(6));
                        Product product = new Product(rs.getString(1), rs.getBigDecimal(2), rs.getString(3), rs.getString(4), productCategory, supplier);
                        product.setId(rs.getInt(7));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductCategory getCategoryById(int id){
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description FROM product_categories WHERE id = (?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            ProductCategory productCategory = null;
            if (rs.next()) {
                productCategory = new ProductCategory(rs.getString(1), rs.getString(2));
            }
            return productCategory;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Supplier getSupplierById(int id){
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
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
