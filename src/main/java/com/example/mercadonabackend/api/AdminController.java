package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.pojo.Product;
import com.example.mercadonabackend.pojo.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

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
        Product product = new Product();
        getNewProductPage.addObject("categoryList", categoryService.getAllCategory());
        getNewProductPage.addObject("product", product);


        return getNewProductPage;
    }

    @GetMapping("/product")
    public ModelAndView productManagement() {
        ModelAndView getProductPage = new ModelAndView();
        getProductPage.setViewName("pages/adminProduct.html");
        getProductPage.addObject("products", productService.getAllProduct());

        return getProductPage;
    }

    @GetMapping("/product/update/{id}")
    public ModelAndView productUpdate(@PathVariable(name = "id")Long id){

        ModelAndView getUpdateFormProduct = new ModelAndView();
        getUpdateFormProduct.setViewName("pages/adminUpdateProduct.html");
        Product currentProduct = productService.getProductById(id);
        getUpdateFormProduct.addObject("categoryList", categoryService.getAllCategory());
        getUpdateFormProduct.addObject("product", currentProduct);

        return getUpdateFormProduct;
    }

    @GetMapping("/add/promotion/{id}")
    public ModelAndView createPromotion(@PathVariable(name = "id")Long id){
        ModelAndView getPromtionPage = new ModelAndView();
        getPromtionPage.setViewName("pages/adminAddPromotion.html");
        Promotion promotion = new Promotion();
        getPromtionPage.addObject("promotion", promotion);
        getPromtionPage.addObject("product", productService.getProductById(id));

        return getPromtionPage;
    }


}
