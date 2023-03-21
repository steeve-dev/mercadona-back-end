package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

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
        Category category1 = new Category();
        getCategoryPage.addObject("newCategory", category1);
        getCategoryPage.addObject("categoryList", categoryService.getAllCategory());

        return getCategoryPage;
    }


    @GetMapping("/product/add")
    public ModelAndView newProduct() {
        ModelAndView getNewProductPage = new ModelAndView();
        getNewProductPage.setViewName("pages/adminNewProduct.html");
        getNewProductPage.addObject("categoryList", categoryService.getAllCategory());


        return getNewProductPage;
    }

    @GetMapping("/product")
    public ModelAndView productManagement() {
        ModelAndView getProductPage = new ModelAndView();
        getProductPage.setViewName("pages/adminProduct.html");

        return getProductPage;
    }
}
