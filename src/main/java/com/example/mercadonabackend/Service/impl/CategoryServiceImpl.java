package com.example.mercadonabackend.Service.impl;


import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategory() {

        return categoryRepository.findAll();
    }

    @Override
    public Category getAllCategoryById(Long id) {
        if(verifId(id)) {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            return categoryOptional.orElse(null);
        }
        else {
            return null;
        }
    }

    @Override
    public void updateCategory(Long categoryId, Category category) {
        this.deleteCategory(categoryId);
        categoryRepository.save(category);
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private boolean verifId(Long id){
        return id != null; //return True si id != 0 sinon, false
    }
}
