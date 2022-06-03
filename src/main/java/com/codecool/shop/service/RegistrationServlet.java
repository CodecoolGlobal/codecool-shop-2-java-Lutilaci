package com.codecool.shop.service;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet (name="Registration", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException {
        DatabaseManager dbManager = new DatabaseManager();
        try{
            dbManager.setup();
        } catch (SQLException e){
            e.printStackTrace();
        }
        String email = req.getParameter("email");
        String password = req.getParameter("psw");
        System.out.println(email+password);
        User user = new User(email, password);
        UserDao userDao = dbManager.getUserDao();
        userDao.add(user);

        ProductDao productDataStore = dbManager.getProductDao();
        ProductCategoryDao productCategoryDataStore = dbManager.getProdCatDao();
        SupplierDao supplierDao = dbManager.getSupplierDao();

        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productCategoryDataStore.getAll());
        context.setVariable("products", productService.getAllProducts());
        context.setVariable("suppliers", productService.getAllSupplier());

        engine.process("product/index.html", context, resp.getWriter());
    }
}
