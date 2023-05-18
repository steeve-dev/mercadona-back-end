package com.example.mercadonabackend.api;

import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.pojo.Product;
import com.example.mercadonabackend.pojo.Promotion;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    public AdminController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView getAdminData() {
        ModelAndView getAdminPage = new ModelAndView();
        getAdminPage.setViewName("admin.html");

        return getAdminPage;
    }


    @GetMapping("/category")
    public ModelAndView categoryManagement() {
        ModelAndView getCategoryPage = new ModelAndView();
        getCategoryPage.setViewName("adminCategory.html");
        Category category1 = new Category();
        getCategoryPage.addObject("newCategory", category1);
        getCategoryPage.addObject("categoryList", categoryService.getAllCategory());

        return getCategoryPage;
    }

    @GetMapping("category/update/{id}")
    public String updateCategoryForm(@PathVariable(name = "id")Long id, Model model){

        model.addAttribute("category", categoryService.getCategoryById(id));
        return "components/admin/categoryUpdateForm.html";
    }


    @GetMapping("/product/add")
    public ModelAndView newProduct() {
        ModelAndView getNewProductPage = new ModelAndView();
        getNewProductPage.setViewName("adminNewProduct.html");
        Product product = new Product();
        getNewProductPage.addObject("categoryList", categoryService.getAllCategory());
        getNewProductPage.addObject("product", product);

        return getNewProductPage;
    }

    @GetMapping("/product")
    public ModelAndView productManagement() {
        ModelAndView getProductPage = new ModelAndView();
        getProductPage.setViewName("adminProduct.html");
        getProductPage.addObject("products", productService.getAllProduct());
        getProductPage.addObject("categoryList", categoryService.getAllCategory());
        return getProductPage;
    }

    @GetMapping("/product/{id}")
    public ModelAndView productManagementByCategory(@PathVariable(name = "id")Long id) {
        ModelAndView getProductPage = new ModelAndView();
        getProductPage.setViewName("adminProduct.html");
        Category currentCategory = categoryService.getCategoryById(id);
        getProductPage.addObject("categoryList", categoryService.getAllCategory());
        getProductPage.addObject("products", productService.getProductByCategory(currentCategory));

        return getProductPage;
    }

    @GetMapping("/product/promotion")
    public ModelAndView productManagementByPromotion() {
        ModelAndView getProductPage = new ModelAndView();
        getProductPage.setViewName("adminProductByPromotion.html");
        getProductPage.addObject("products", productService.getAllProduct());
        getProductPage.addObject("categoryList", categoryService.getAllCategory());
        return getProductPage;
    }

    @GetMapping("/product/update/{id}")
    public ModelAndView productUpdate(@PathVariable(name = "id")Long id){

        ModelAndView getUpdateFormProduct = new ModelAndView();
        getUpdateFormProduct.setViewName("adminUpdateProduct.html");
        Product currentProduct = productService.getProductById(id);
        getUpdateFormProduct.addObject("categoryList", categoryService.getAllCategory());
        getUpdateFormProduct.addObject("product", currentProduct);

        return getUpdateFormProduct;
    }

    @GetMapping("/add/promotion/{id}")
    public ModelAndView createPromotion(@PathVariable(name = "id")Long id){
        ModelAndView getPromtionPage = new ModelAndView();
        getPromtionPage.setViewName("adminAddPromotion.html");
        Promotion promotion = new Promotion();
        getPromtionPage.addObject("promotion", promotion);
        getPromtionPage.addObject("product", productService.getProductById(id));

        return getPromtionPage;
    }
}
