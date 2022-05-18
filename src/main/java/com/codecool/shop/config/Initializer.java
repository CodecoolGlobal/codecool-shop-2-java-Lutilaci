package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory fun = new ProductCategory("Fun items", "fun", "Unnecessary fun items for boring days.");
        productCategoryDataStore.add(fun);
        ProductCategory home = new ProductCategory("Home accessories", "home", "These utilities might come in handy one day.");
        productCategoryDataStore.add(home);
        ProductCategory kitchen = new ProductCategory("Kitchen supplies", "kitchen", "Everyone needs some kitchen supplies.");
        productCategoryDataStore.add(kitchen);

        //setting up products and printing it
        productDataStore.add(new Product("Sunglass", new BigDecimal("49.9"), "USD", "For sunny days.", fun, amazon));
        productDataStore.add(new Product("Candle Bulb", new BigDecimal("47.9"), "USD", "For night.", home, lenovo));
        productDataStore.add(new Product("Bottle Water", new BigDecimal("89"), "USD", "Thirst trap.", kitchen, amazon));
    }
}
