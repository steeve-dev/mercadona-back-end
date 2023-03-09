package com.example.mercadonabackend.Service;

import com.example.mercadonabackend.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategory();

    Category getAllCategoryById(Long id);

    void updateCategory (Long categoryId, Category category);

    void createCategory(Category category);

    void deleteCategory(Long id);

}
