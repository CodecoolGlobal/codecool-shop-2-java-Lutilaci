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

@WebServlet(name = "productsById",urlPatterns = {"/api/get/product"})
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProductDao productDataStore = ProductDaoMem.getInstance();

        int prodid = Integer.parseInt(request.getParameter("prodid"));
        Product product = productDataStore.find(prodid);
        Gson gson = new Gson();
        String json = "";
        json = gson.toJson(product);
        response.getOutputStream().print(json);
    }
}
