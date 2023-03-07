package com.example.mercadonabackend.Service;

import com.example.mercadonabackend.pojo.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    List<Category> getAllCategoryByName(String name);

    void updateCategory (Long categoryId, Category category);

    void createCategory(Category category);

    void deleteCategory(Long id);


}
