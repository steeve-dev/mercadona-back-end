package com.example.mercadonabackend.api;

import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.pojo.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestProductWs {

    @Autowired
    private CategoryService categoryService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductWs controller;

    @Autowired
    private ProductWs productWs;


    @Test
    @Transactional
    public void testCreateProduct() {
        Category category = new Category();
        category.setName("test");
        categoryService.createCategory(category);

        Product product = new Product();
        product.setName("Produit Test");
        product.setDescription("Ceci est un produit test");
        product.setPrice(2.5f);
        product.setCategory(category);
        product.setImageLink("../../../../images/product/boissons.jpg");

        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);

        String result = productWs.createProduct(product, bindingResult, model);

        assertEquals("redirect:/admin/product", result);
        assertNotNull(product.getId());
    }

    @Test
    public void testDeleteProduct() {
        // Given
        Long id = 1L;
        doNothing().when(productService).deleteProduct(id);

        // When
        String result = controller.deleteProduct(id);

        // Then
        verify(productService, times(1)).deleteProduct(id);
        assertEquals("redirect:/admin/product", result);
    }

}
