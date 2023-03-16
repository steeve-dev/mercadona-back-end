package com.example.mercadonabackend.api;


import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public ModelAndView getAdminData() {
        ModelAndView getAdminPage = new ModelAndView();
        getAdminPage.setViewName("pages/admin.html");

        return getAdminPage;
    }

    @GetMapping("/category")
    public ModelAndView categoryManagement() {
        ModelAndView getCategoryPage = new ModelAndView();
        getCategoryPage.setViewName("pages/adminCategory.html");

        return getCategoryPage;
    }

    @GetMapping("/product/add")
    public ModelAndView newProduct() {
        ModelAndView getnewProductPage = new ModelAndView();
        getnewProductPage.setViewName("pages/adminNewProduct.html");

        return getnewProductPage;
    }

    @GetMapping("/product")
    public ModelAndView productManagement() {
        ModelAndView getProductPage = new ModelAndView();
        getProductPage.setViewName("pages/adminProduct.html");

        return getProductPage;
    }




}
