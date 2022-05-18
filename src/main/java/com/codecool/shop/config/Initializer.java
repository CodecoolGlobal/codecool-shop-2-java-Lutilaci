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
        productDataStore.add(new Product("Sunglass", new BigDecimal("12"), "USD", "For sunny days.", fun, amazon));
        productDataStore.add(new Product("Candle Bulb", new BigDecimal("5"), "USD", "For candle-lit date nights.", home, lenovo));
        productDataStore.add(new Product("Bottle Water", new BigDecimal("3"), "USD", "Definitely a thirst trap.", kitchen, amazon));
        productDataStore.add(new Product("Self-watering can", new BigDecimal("20"), "USD", "Never run out of water.", home, amazon));
        productDataStore.add(new Product("Aquarium Hourglass", new BigDecimal("15"), "USD", "Don't worry about time, but feeding your new pet.", fun, amazon));
        productDataStore.add(new Product("Table Tennis equipment", new BigDecimal("8"), "USD", "There's a loophole.", fun, amazon));
        productDataStore.add(new Product("Couple's mugs", new BigDecimal("30"), "USD", "For you and your clingy partner.", kitchen, amazon));
        productDataStore.add(new Product("Double Champagne Glass", new BigDecimal("50"), "USD", "Double the fun on New Year's Eve.", kitchen, amazon));
        productDataStore.add(new Product("Pocket Ruler", new BigDecimal("6"), "USD", "To measure every tiny detail.", home, amazon));
        productDataStore.add(new Product("Balloon Paint Roller", new BigDecimal("45"), "USD", "Mix a birthday party with home renovation.", home, amazon));
        productDataStore.add(new Product("Spherical dice", new BigDecimal("25"), "USD", "Test your luck.", fun, amazon));
        productDataStore.add(new Product("Spork", new BigDecimal("12"), "USD", "If you can't decide.", kitchen, amazon));
    }
}
