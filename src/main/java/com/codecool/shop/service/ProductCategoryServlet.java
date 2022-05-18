package com.codecool.shop.service;


import javax.servlet.annotation.WebServlet;

@WebServlet(name="prodCatServlet", urlPatterns = {"/sg/something"}, loadOnStartup = 2)
public class ProductCategoryServlet extends BasicServlet {

    public ProductCategoryServlet() {
        this.setServletURL("something from url");
    }
}
