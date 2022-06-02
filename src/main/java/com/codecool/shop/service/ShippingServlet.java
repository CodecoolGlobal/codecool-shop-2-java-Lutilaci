package com.codecool.shop.service;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Address;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name="OrderDetails", urlPatterns = {"/api/post/order-details"})
public class ShippingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException {
        String firstName = req.getParameter("f-name");
        String lastName = req.getParameter("l-name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String zip = req.getParameter("zip");

        Address orderDetails = new Address(firstName, lastName, address, city, zip);


        System.out.println(orderDetails.firstName);
        System.out.println(orderDetails.lastName);
        System.out.println(orderDetails.street);
        System.out.println(orderDetails.city);
        System.out.println(orderDetails.zipCode);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("firstName", firstName);
        context.setVariable("lastName", lastName);
        context.setVariable("address", address);
        context.setVariable("city", city);
        context.setVariable("zip", zip);
        context.setVariable("email", email);



        engine.process("checkout/order-summary.html", context, resp.getWriter());


        // TODO: create new object and set its fields from request!
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("checkout/order-summary.html", context, resp.getWriter());

    }
}
