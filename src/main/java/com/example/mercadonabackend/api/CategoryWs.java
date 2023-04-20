package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.CategoryService;
import org.springframework.stereotype.Controller;
import com.example.mercadonabackend.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_CATEGORY)
public class CategoryWs {

    @Autowired
    private CategoryService service;

    @PutMapping("{id}")
    public void updateCategory(@PathVariable(name = "id") Long id,
                               Category category){
        service.updateCategory(id, category);
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
