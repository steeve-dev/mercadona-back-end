package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.CategoryService;
import org.springframework.stereotype.Controller;
import com.example.mercadonabackend.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_CATEGORY)
public class CategoryWs {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ModelAndView getAllCategory()
    {
        ModelAndView getCategory = new ModelAndView();
        getCategory.setViewName("index.html");
        getCategory.addObject("category", service.getAllCategory());

        return getCategory;
    }

    @GetMapping("{id}")
    public Category getAllCategoryById(@PathVariable(name="id") Long id)
    {
        return service.getAllCategoryById(id);
    }

    @PostMapping
    public void createCategory(@RequestBody Category category){

        service.createCategory(category);
    }

    @PutMapping("{id}")
    public void updateCategory(@PathVariable(name = "id") Long id,
                               @RequestBody Category category){
        service.updateCategory(id, category);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable(name = "id") Long id){
        service.deleteCategory(id);
    }
}
