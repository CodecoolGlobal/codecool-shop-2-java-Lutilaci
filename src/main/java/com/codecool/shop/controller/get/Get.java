package com.codecool.shop.controller.get;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/get/product-attributes"})
public class Get extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);

//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
        String prodId = req.getParameter("prodId");

//        List<Product> playground = productService.getProductsForCategory(1);
        Product productList = productDataStore.find(Integer.parseInt(prodId));
        Gson gson = new Gson();
        String json = "";
        json += gson.toJson(productList);
        resp.getOutputStream().print(json);
//        for(Product product: playground){
//            product.setProductCategory(new ProductCategory("Valami", "Valami","valami"));
//            product.getSupplier().setProducts(new ArrayList<>());
//        }
//        String json = gson.toJson(playground.get(0));
//        resp.getOutputStream().print(json);
    }
}
