package com.example.mercadonabackend.Service;

import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.pojo.Product;

import java.util.Collection;
import java.util.List;


public interface CategoryService {

    List<Category> getAllCategory();

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    void updateCategory (Long categoryId, String newName);

    void createCategory(Category category);

    void deleteCategory(Long id);


}
