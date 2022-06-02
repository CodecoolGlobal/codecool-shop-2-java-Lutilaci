package com.codecool.shop.service;


import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "allProduct", urlPatterns = {"/api/products"})
public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        DatabaseManager dbManager = new DatabaseManager();
        try{
            dbManager.setup();
        } catch (SQLException e){
            e.printStackTrace();
        }
        //TODO : Make it generic
        ProductDao productDataStore = dbManager.getProductDao();
        ProductCategoryDao productCategoryDataStore = dbManager.getProdCatDao();
        SupplierDao supplierDao = dbManager.getSupplierDao();
//
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDao = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);

        String catId = request.getParameter("catid");
        String suppId = request.getParameter("suppid");
        String multipleProduct = request.getParameter("products");

        List<Product> products = new ArrayList<>();
        Gson gson = new Gson();

        String json = "";
        if(multipleProduct != null){ // FOR CART PRODUCTS ON PAGE LOADING
            List<String> productIds = List.of(multipleProduct.split(","));
            List<Product> placeholder = productService.getAllProducts();
            List<Product> finalProducts = products;
            for(int i = 0; i < productIds.size(); i++){
                int id = Integer.parseInt(productIds.get(i));
                placeholder.forEach(product -> {
                    if(product.getId() == id){
                        finalProducts.add(product);
                    }
                });
            }
            json += gson.toJson(finalProducts);
        } else if (catId != null && suppId != null){ // FOR FILTERING
            List<Product> placeholder = productService.getProductsForCategory(Integer.parseInt(catId));
            List<Product> providedProducts = new ArrayList<>();
            placeholder.forEach(product -> {
                if(supplierDao.find(Integer.parseInt(suppId)) == product.getSupplier()){
                    providedProducts.add(product);
                }
            });
            products = providedProducts;
            json += gson.toJson(products);
        } else if (catId != null){
            products = productService.getProductsForCategory(Integer.parseInt(catId));
            json += gson.toJson(products);
        } else if (suppId != null){
            products = supplierDao.find(Integer.parseInt(suppId)).getProducts();
            json += gson.toJson(products);
        } else {
            products = productService.getAllProducts();
            json += gson.toJson(products);
        }
        response.getOutputStream().print(json);
    }
}