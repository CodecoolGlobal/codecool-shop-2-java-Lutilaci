package com.codecool.shop.service;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.OrderDetails;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name="OrderDetails", urlPatterns = {"/api/post/order-details"})
public class ShippingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException {
        List<String> customer = new ArrayList<>();
        String firstName = req.getParameter("f-name");
        String lastName = req.getParameter("l-name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String zip = req.getParameter("zip");

        customer.add(firstName);
        customer.add(lastName);
        customer.add(email);
        customer.add(address);
        customer.add(city);
        customer.add(zip);


        OrderDetails orderDetails = new OrderDetails(firstName, lastName, email, address, city, zip);

        System.out.println(orderDetails.firstName);
        System.out.println(orderDetails.lastName);
        System.out.println(orderDetails.email);
        System.out.println(orderDetails.street);
        System.out.println(orderDetails.city);
        System.out.println(orderDetails.zipCode);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("costumer", customer);

        engine.process("checkout/order-summary.html", context, resp.getWriter());


        // TODO: create new object and set its fields from request!
    }
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//
//        engine.process("checkout/order-summary.html", context, resp.getWriter());
//
//    }
}
