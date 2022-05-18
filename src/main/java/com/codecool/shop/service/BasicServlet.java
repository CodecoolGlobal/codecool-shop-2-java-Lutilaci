package com.codecool.shop.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class BasicServlet extends HttpServlet {

    private String servletURL;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Setting respone content type
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // Asking for query parameter
        String page = request.getParameter("page");
        URL url;
        // Checking if there was any query parameter
        if(page != null){
            url = new URL("http://localhost:8080/" + servletURL + "/" + page + ".json");
        } else {
            url = new URL("http://localhost:8080/" + servletURL + "/1.json");
        }
        // Setting up connection with the hacker api
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        // getting the input Buffer from the connection
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        // Adding the lines to the string builder to build the json response as a string
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        // returning the string we got from the hacker api
        PrintWriter out = response.getWriter();

        out.println(content);
        out.flush();


    }
    public String getHackerURL() {
        return servletURL;
    }

    public void setServletURL(String servletURL) {
        this.servletURL = servletURL;
    }
}

