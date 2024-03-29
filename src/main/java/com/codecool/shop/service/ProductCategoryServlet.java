package com.codecool.shop.service;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "productsByCategory",urlPatterns = {"/api/get/products-by-category"})
public class ProductCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);
        
        int catId = Integer.parseInt(request.getParameter("catId"));
        List<Product> productList = productService.getProductsForCategory(catId);

        Gson gson = new Gson();
        String json = "";
        json += gson.toJson(productList);
        response.getOutputStream().print(json);
    }
}
