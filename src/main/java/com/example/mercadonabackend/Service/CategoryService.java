package com.example.mercadonabackend.Service;

import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.pojo.Product;

import java.util.Collection;
import java.util.List;


public interface CategoryService {

    List<Category> getAllCategory();

    Category getCategoryById(Long id);

    List<Product> getProductOfCategoryId(Long id);

    void updateCategory (Long categoryId, String newName);

    void createCategory(Category category);

    void deleteCategory(Long id);

}
