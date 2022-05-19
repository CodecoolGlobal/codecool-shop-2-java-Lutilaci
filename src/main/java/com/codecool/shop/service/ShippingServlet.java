package com.codecool.shop.service;

import com.codecool.shop.model.OrderDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShippingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String firstName = req.getParameter("f-name");
        String lastName = req.getParameter("l-name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String zip = req.getParameter("zip");

        OrderDetails orderDetails = new OrderDetails(firstName, lastName, email, address, city, zip);

        System.out.println(orderDetails);
        // TODO: create new object and set its fields from request!
    }
}
