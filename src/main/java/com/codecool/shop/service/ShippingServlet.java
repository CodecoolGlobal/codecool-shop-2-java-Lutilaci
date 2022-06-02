package com.codecool.shop.service;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Address;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet (name="OrderDetails", urlPatterns = {"/api/post/order-details"})
public class ShippingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException {
        DatabaseManager dbManager = new DatabaseManager();
        try{
            dbManager.setup();
        } catch (SQLException e){
            e.printStackTrace();
        }
        //PRODUCTS
        String productIds = req.getParameter("productIds");
        List<String> listOfProductId = List.of(productIds.split(","));

        //USER ID FOR THE PRODUCTS THROUGH THE EMAIL PARAMETER
        UserDao userDao = dbManager.getUserDao();
        String email = req.getParameter("email");
        int userId = userDao.findIdByEmail(email);

        //CREATE AN ADDRESS OBJECT
        Address address = new Address(userId,
                req.getParameter("f-name"),
                req.getParameter("l-name"),
                req.getParameter("address"),
                req.getParameter("city"),
                req.getParameter("zip"));

        dbManager.getAddressDao().add(address);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("firstName", address.getFirstName());
        context.setVariable("lastName", address.getLastName());
        context.setVariable("address", address.getStreet());
        context.setVariable("city", address.getCity());
        context.setVariable("zip", address.getZipCode());
        context.setVariable("email", email);

        engine.process("checkout/order-summary.html", context, resp.getWriter());
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("checkout/order-summary.html", context, resp.getWriter());

    }
}
