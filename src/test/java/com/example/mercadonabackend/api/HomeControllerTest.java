package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.pojo.Category;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private HomeController homeController;

    @Test
    public void testGetHomeData() {
        // simuler le comportement de getAllCategory
        List<Category> mockCategoryList = new ArrayList<>();
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Category1");
        mockCategoryList.add(category1);
        Mockito.when(categoryService.getAllCategory()).thenReturn(mockCategoryList);

        ModelAndView result = homeController.getHomeData();
        assertEquals("index.html", result.getViewName());
        assertEquals(mockCategoryList, result.getModel().get("categoryList"));
        // vérifier que la méthode getAllProduct est appelée une fois
        Mockito.verify(productService, Mockito.times(1)).getAllProduct();
    }






}
