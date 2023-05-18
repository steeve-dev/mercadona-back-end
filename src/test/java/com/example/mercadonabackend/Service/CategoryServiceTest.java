package com.example.mercadonabackend.Service;

import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.repository.CategoryRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryServiceTest {

    @Mock
    private CategoryService service;

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setId(2L);
        category.setName("test");

        service.createCategory(category);

        assertEquals("test", category.getName());
    }

    @Test
    public void testDeleteCategory() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("testDelete");

        // Save category to repository
        repository.save(category);

        // And delete
        service.deleteCategory(categoryId);

        Optional<Category> deletedCategory = categoryRepository.findById(categoryId);
        assertFalse(deletedCategory.isPresent());
    }


    @Test
    public void testGetCategoryByNameAndId() {
        // Given
        String categoryName = "Test Category";
        Long categoryId = 1L;
        Category expectedCategory = new Category(categoryId, categoryName);

        when(repository.findByName(categoryName)).thenReturn(expectedCategory);
        when(repository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        // When
        Category actualCategory = categoryService.getCategoryByName(categoryName);
        Category categoryById = categoryService.getCategoryById(categoryId);

        assertEquals(expectedCategory, actualCategory);
        assertEquals(expectedCategory, categoryById);

        // Then
        verify(repository, times(1)).findByName(categoryName);
        verify(repository, times(1)).findById(categoryId);
    }

    @Test
    public void testUpdateCategory() {
        // Given
        Long categoryId = 1L;
        String newName = "New Category Name";
        Category category = new Category(categoryId, "Old Category Name");

        when(repository.findById(categoryId)).thenReturn(Optional.of(category));
        when(repository.save(category)).thenReturn(category);

        // When
        categoryService.updateCategory(categoryId, newName);

        // Then
        verify(repository, times(1)).findById(categoryId);
        verify(repository, times(1)).save(category);

        assertEquals(newName, category.getName());
    }



}
