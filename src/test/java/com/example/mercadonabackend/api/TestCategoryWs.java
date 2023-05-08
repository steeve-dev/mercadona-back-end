package com.example.mercadonabackend.api;

import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.pojo.Category;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCategoryWs {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryWs controller;

    @Autowired
    private CategoryWs categoryWs;

    // test of category creation
    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("test category");
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);
        String result = categoryWs.createCategory(category, bindingResult, model);
        assertEquals("redirect:/admin/category", result);
    }

    @Test
    public void testDeleteCategory(){

        Long id = 1L;
        doNothing().when(categoryService).deleteCategory(id);

        String result = controller.deleteCategory(id);

        verify(categoryService, times(1)).deleteCategory(id);
        assertEquals("redirect:/admin/category", result);

    }



}
