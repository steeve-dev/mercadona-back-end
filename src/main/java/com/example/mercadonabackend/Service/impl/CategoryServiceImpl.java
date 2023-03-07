package com.example.mercadonabackend.Service.impl;


import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    private static final List<Category> myList = new ArrayList<>();


    @Override
    public List<Category> getAllCategory() {
        return myList;
    }

    @Override
    public List<Category> getAllCategoryByName(String name) {
        return myList.stream()
                .filter(category -> category.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCategory(Long categoryId, Category category) {
        this.deleteCategory(categoryId);
        myList.add(category);
    }

    @Override
    public void createCategory(Category category) {
    }

    @Override
    public void deleteCategory(Long id) {
        myList.removeIf(myCategory ->myCategory.getId().equals(id));
    }
}
