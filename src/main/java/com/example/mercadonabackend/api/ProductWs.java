package com.example.mercadonabackend.api;

import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_PRODUCT)
public class ProductWs {
    @Autowired
    private ProductService service;

    @GetMapping()
    public List<Product> getAllProduct(){
        return service.getAllProduct();
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable(name="id")Long id){
        return service.getProductById(id);
    }

    @PutMapping("{id}")
    public void updateProduct(@PathVariable(name="id")Long id, @RequestBody Product product){
        service.updateProduct(id, product);
    }
    @PostMapping
    public void createProduct(@RequestBody Product product){
        service.createProduct(product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable(name = "id")Long id){
        service.deleteProduct(id);
    }
}
