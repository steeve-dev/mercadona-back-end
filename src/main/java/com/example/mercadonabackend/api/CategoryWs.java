package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.CategoryService;
import org.springframework.stereotype.Controller;
import com.example.mercadonabackend.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_CATEGORY)
public class CategoryWs {

    @Autowired
    private CategoryService service;

    @PostMapping("{id}")
    public String updateCategory(@PathVariable(name = "id") Long id, @RequestParam(name = "newName") String newName){
        service.updateCategory(id, newName);
        return "redirect:/admin/category";
    }

    @PostMapping("/post")
    public String createCategory(Category category){

        service.createCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Long id){
        service.deleteCategory(id);
        return "redirect:/admin/category";
    }

}
