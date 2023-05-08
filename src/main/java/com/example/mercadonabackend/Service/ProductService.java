package com.example.mercadonabackend.Service;


import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.pojo.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    List<Product> getProductByCategory(Category category);

    Product getProductById(Long id);

    void updateProduct(Long productId, Product product);

    void createProduct(Product product);

    void deleteProduct(Long id);
}
