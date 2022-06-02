package com.codecool.shop.service;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="productsBySupplier", urlPatterns = {"/api/get/products-by-supplier"})
public class ProductSupplierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProductDao productDataStore = ProductDaoMem.getInstance();

        int supId = Integer.parseInt(request.getParameter("supId"));

        List<Product> productList = productDataStore.getAll();
        List<Product> productsNeeded = new ArrayList<>();

        if(productList.size() > 0){
            for(Product product: productList){
                if (product.getSupplier().getId() == supId) {
                    productsNeeded.add(product);
                }
            }
        }

        Gson gson = new Gson();
        String json = "";

        if(productsNeeded.size() > 0){
            for(int i = 0; i < productsNeeded.size(); i++){
                json += gson.toJson(productsNeeded.get(i));
            }
        } else {
            json = gson.toJson(null);
        }

        response.getOutputStream().print(json);
    }
}
