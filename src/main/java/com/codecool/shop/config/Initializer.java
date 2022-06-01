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

//        //setting up a new supplier
//        Supplier CycleOps = new Supplier("Cycle-ops");
//        supplierDataStore.add(CycleOps);
//        Supplier LooneyTools = new Supplier("Looney Tools");
//        supplierDataStore.add(LooneyTools);
//
//        //setting up a new product category
//        ProductCategory fun = new ProductCategory("fun", "Unnecessary fun items for boring days.");
//        productCategoryDataStore.add(fun);
//        ProductCategory home = new ProductCategory("home", "These utilities might come in handy one day.");
//        productCategoryDataStore.add(home);
//        ProductCategory kitchen = new ProductCategory( "kitchen", "Everyone needs some kitchen supplies.");
//        productCategoryDataStore.add(kitchen);
//
//        //setting up products and printing it
//        productDataStore.add(new Product("Sunglass", new BigDecimal("12"), "USD", "For sunny days.", fun, CycleOps));
//        productDataStore.add(new Product("Candle Bulb", new BigDecimal("5"), "USD", "For candle-lit date nights.", home, CycleOps));
//        productDataStore.add(new Product("Bottle Water", new BigDecimal("3"), "USD", "Definitely a thirst trap.", kitchen, LooneyTools));
//        productDataStore.add(new Product("Self-watering can", new BigDecimal("20"), "USD", "Never run out of water.", home, LooneyTools));
//        productDataStore.add(new Product("Aquarium Hourglass", new BigDecimal("15"), "USD", "Don't worry about time, but feeding your new pet.", fun, LooneyTools));
//        productDataStore.add(new Product("Table Tennis equipment", new BigDecimal("8"), "USD", "There's a loophole.", fun, CycleOps));
//        productDataStore.add(new Product("Couple's mugs", new BigDecimal("30"), "USD", "For you and your clingy partner.", kitchen, LooneyTools));
//        productDataStore.add(new Product("Double Champagne Glass", new BigDecimal("50"), "USD", "Double the fun on New Year's Eve.", kitchen, CycleOps));
//        productDataStore.add(new Product("Pocket Ruler", new BigDecimal("6"), "USD", "To measure every tiny detail.", home, LooneyTools));
//        productDataStore.add(new Product("Balloon Paint Roller", new BigDecimal("45"), "USD", "Mix a birthday party with home renovation.", home, CycleOps));
//        productDataStore.add(new Product("Spherical dice", new BigDecimal("25"), "USD", "Test your luck.", fun, LooneyTools));
//        productDataStore.add(new Product("Spork", new BigDecimal("10"), "USD", "If you can't decide.", kitchen, CycleOps));
//        productDataStore.add(new Product("SOUPer Bowl", new BigDecimal("12"), "USD", "Can't really hold liquid.", kitchen, LooneyTools));
//        productDataStore.add(new Product("Rocking ladder", new BigDecimal("52"), "USD", "Swing by the roof", home, CycleOps));
//        productDataStore.add(new Product("Glass", new BigDecimal("22"), "USD", "For fashion, not prescription.", fun, LooneyTools));
//        productDataStore.add(new Product("Self-hammer", new BigDecimal("35"), "USD", "You're gonna nail the home reno.", home, CycleOps));
//        productDataStore.add(new Product("Feather Knife", new BigDecimal("25"), "USD", "Chicks love it.", kitchen, CycleOps));
//        productDataStore.add(new Product("Booth trush", new BigDecimal("9"), "USD", "Clean 'em from a different angle.", fun, LooneyTools));


    }
}
