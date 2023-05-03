package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    private final ProductService productService;

    @GetMapping
    public ModelAndView getHomeData()
    {
        ModelAndView getCategory = new ModelAndView();
        getCategory.setViewName("index.html");
        getCategory.addObject("categoryList", categoryService.getAllCategory());
        getCategory.addObject("products", productService.getAllProduct());

        return getCategory;
    }

    @GetMapping("category/{id}")
    public String getProductByCategory(@PathVariable(name = "id")Long id, Model model)
    {

        Category currentCategory = categoryService.getCategoryById(id);
        model.addAttribute("categoryList", categoryService.getAllCategory());
        model.addAttribute("products", productService.getProductByCategory(currentCategory));

        return "components/home/productCard.html";
    }


    @GetMapping("/promotion")
    public String getProductWithPromotion2(Model model){

        model.addAttribute("categoryList", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        return "components/home/getPromotion.html";
    }


    public HomeController(ProductService productService) {
        this.productService = productService;
    }
}
