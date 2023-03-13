package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_CATEGORY)
public class CategoryWs {

    @Autowired
    private CategoryService service;

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory()
    {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Get-Header", "ExampleHeader");
        return ResponseEntity.ok().headers(headers).body(service.getAllCategory());
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
