package com.example.mercadonabackend.Service.impl;


import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List getAllCategory() {

        return categoryRepository.findAll();
    }

    @Override
    public Category getAllCategoryById(Long id) {
        return null;
    }

    @Override
    public void updateCategory(Long categoryId, Category category) {

    }

    @Override
    public void createCategory(Category category) {

    }

    @Override
    public void deleteCategory(Long id) {

    }


    private boolean verifId(Long id){
        return id != null; //return True si id != 0 sinon, false
    }
}
