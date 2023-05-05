package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.CategoryService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import com.example.mercadonabackend.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_CATEGORY)
public class CategoryWs {

    @Autowired
    private CategoryService service;

    @PostMapping("{id}")
    public String updateCategory(@PathVariable(name = "id") Long id, @RequestParam(name = "newName") @Size(min = 1, max = 50) String newName, Model model){
        try {
            service.updateCategory(id, newName);
        } catch (Exception e){
            model.addAttribute("categoryList", service.getAllCategory());
            model.addAttribute("newCategory", new Category());
            model.addAttribute("errorUpdateMessage", "La saisie doit être une chaine de caractères entre 1 et 50 caractères.");
            return "adminCategory.html";
        }
        model.addAttribute("newCategory", new Category());
        model.addAttribute("categoryList", service.getAllCategory());
        return "adminCategory.html";
    }

    @PostMapping("/post")
    public String createCategory(@Valid Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newCategory", category);
            model.addAttribute("categoryList", service.getAllCategory());
            model.addAttribute("errorMessage", "Le nom de la catégorie doit contenir entre 1 et 50 caractère.");
            return "adminCategory.html";
        }
        model.addAttribute("newCategory", category);
        model.addAttribute("categoryList", service.getAllCategory());
        service.createCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Long id){
        service.deleteCategory(id);
        return "redirect:/admin/category";
    }

}
