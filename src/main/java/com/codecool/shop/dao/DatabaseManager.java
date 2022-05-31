package com.codecool.shop.dao;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import org.postgresql.ds.PGSimpleDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseManager {

    private ProductDao productDao;
    private ProductCategoryDao prodCatDao;
    private SupplierDao supplierDao;

    public void setup() throws SQLException, IOException {
        DataSource dataSource = connect();
        productDao = new ProductDaoJdbc(dataSource);

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