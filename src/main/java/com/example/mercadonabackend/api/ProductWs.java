package com.example.mercadonabackend.api;

import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.pojo.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_PRODUCT)
public class ProductWs {
    @Autowired
    private ProductService service;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/put/{id}")
    public String updateProduct(@PathVariable(name="id")Long id, @Valid Product product, BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("; "));
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("categoryList", categoryService.getAllCategory());
            model.addAttribute("newProduct", product);

            return "adminUpdateProduct.html";
        }
        service.updateProduct(id, product);
        redirectAttributes.addFlashAttribute("successMessage", "Le produit a été modifié avec succès !");
        return "redirect:/admin/product";
    }

    @PostMapping("/post")
    public String createProduct(@Valid Product product, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("; "));
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("categoryList", categoryService.getAllCategory());
            model.addAttribute("newProduct", new  Product());

            return "adminNewProduct.html";
        }
        service.createProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Le produit a été créé avec succès !");
        return "redirect:/admin/product";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable(name = "id")Long id, RedirectAttributes redirectAttributes){
        service.deleteProduct(id);
        redirectAttributes.addFlashAttribute("successMessage", "Le produit a bien été supprimé !");
        return "redirect:/admin/product";
    }
}
