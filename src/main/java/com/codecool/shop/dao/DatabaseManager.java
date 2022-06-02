package com.codecool.shop.dao;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.codecool.shop.dao.implementation.jdbc.*;
import org.postgresql.ds.PGSimpleDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseManager {

    private ProductDao productDao;
    private ProductCategoryDao prodCatDao;
    private SupplierDao supplierDao;
    private UserDao userDao;
    private AddressDao addressDao;

    public void setup() throws SQLException, IOException {
        DataSource dataSource = connect();
        prodCatDao = new ProductCategoryDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
        productDao = new ProductDaoJdbc(dataSource, prodCatDao, supplierDao);
        userDao = new UserDaoJdbc(dataSource);
        addressDao = new AddressDaoJdbc(dataSource);
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

    public ProductDao getProductDao() {
        return productDao;
    }
    public ProductCategoryDao getProdCatDao() {
        return prodCatDao;
    }
    public SupplierDao getSupplierDao() {
        return supplierDao;
    }
    public UserDao getUserDao() {
        return userDao;
    }

    public AddressDao getAddressDao() {
        return addressDao;
    }
}