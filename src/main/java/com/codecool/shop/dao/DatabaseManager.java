package com.codecool.shop.dao;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.codecool.shop.dao.implementation.jdbc.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.jdbc.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.jdbc.SupplierDaoJdbc;
import org.postgresql.ds.PGSimpleDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseManager {

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductCategoryDao getProdCatDao() {
        return prodCatDao;
    }

    public void setProdCatDao(ProductCategoryDao prodCatDao) {
        this.prodCatDao = prodCatDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    private ProductDao productDao;
    private ProductCategoryDao prodCatDao;
    private SupplierDao supplierDao;

    public void setup() throws SQLException, IOException {
        DataSource dataSource = connect();
        prodCatDao = new ProductCategoryDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
        productDao = new ProductDaoJdbc(dataSource, prodCatDao, supplierDao);

    }

    private DataSource connect() throws SQLException, IOException, FileNotFoundException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String name = prop.getProperty("db.name");
            String host = prop.getProperty("db.host");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            dataSource.setDatabaseName(name);
            dataSource.setUser(user);
            dataSource.setPassword(password);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dataSource.getConnection().close();

        return dataSource;
    }
}