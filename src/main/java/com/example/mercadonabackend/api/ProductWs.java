package com.example.mercadonabackend.api;

import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.pojo.Product;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_PRODUCT)
public class ProductWs {
    @Autowired
    private ProductService service;

    @PostMapping("/put/{id}")
    public String updateProduct(@PathVariable(name="id")Long id, Product product){
        service.updateProduct(id, product);
        return "redirect:/admin/product";
    }

    @PostMapping("/post")
    public String createProduct(Product product){
        service.createProduct(product);
        return "redirect:/admin/product/add";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable(name = "id")Long id){
        service.deleteProduct(id);
        return "redirect:/admin/product";
    }
}
