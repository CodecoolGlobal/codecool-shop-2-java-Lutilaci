package com.codecool.shop.service;


import javax.servlet.annotation.WebServlet;

@WebServlet(name="prodSuppServlet", urlPatterns = {"/"}, loadOnStartup = 2)
public class ProductSupplierServlet extends BasicServlet {


    public ProductSupplierServlet() {

    }
}
