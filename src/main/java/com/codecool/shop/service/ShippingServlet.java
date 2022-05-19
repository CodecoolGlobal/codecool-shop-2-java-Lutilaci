package com.codecool.shop.service;

import com.codecool.shop.model.OrderDetails;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (name="OrderDetails", urlPatterns = {"/api/post/order-details"})
public class ShippingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException {

        String firstName = req.getParameter("f-name");
        String lastName = req.getParameter("l-name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String zip = req.getParameter("zip");

        OrderDetails orderDetails = new OrderDetails(firstName, lastName, email, address, city, zip);

        System.out.println(orderDetails.firstName);
        System.out.println(orderDetails.lastName);
        System.out.println(orderDetails.email);
        System.out.println(orderDetails.street);
        System.out.println(orderDetails.city);
        System.out.println(orderDetails.zipCode);
        // TODO: create new object and set its fields from request!
    }
}
