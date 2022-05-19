package com.codecool.shop.service;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
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
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);
        
        int catId = Integer.parseInt(request.getParameter("catId"));
        List<Product> productList = productService.getProductsForCategory(catId);

        Gson gson = new Gson();
        String json = "";

        if(productList.size() > 0){
            for(int i = 0; i < productList.size(); i++){
                json += gson.toJson(productList.get(i));
            }
        } else {
            json = gson.toJson(null);
        }
        //Return Json response's first iteration
        response.getOutputStream().print(json);
    }
}
