package com.codecool.shop.service;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "allProduct", urlPatterns = {"/api/products"})
public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);

        String catId = request.getParameter("catid");
        String suppId = request.getParameter("suppid");

        List<Product> products;
        Gson gson = new Gson();
        String json = "";

        if(catId != null && suppId != null){
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