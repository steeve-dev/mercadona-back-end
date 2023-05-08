package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.Service.PromotionService;
import com.example.mercadonabackend.pojo.Product;
import com.example.mercadonabackend.pojo.Promotion;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TestPromotionWs {


    @Mock
    private Model model;
    @Mock
    private PromotionService service;

    @InjectMocks
    private PromotionWs controller;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProductService productService;

    @Test
    public void testCreatePromotion() {
        // Créer un nouveau produit pour la promotion
        Product product = new Product();
        product.setName("Produit de test");
        product.setDescription("Description du produit de test");
        product.setPrice(10.0f);
        productService.createProduct(product);

        // Create new promotion
        Promotion promotion = new Promotion();
        promotion.setProduct(product);
        promotion.setPercentage(20);
        promotion.setBeginDate(LocalDate.now());
        promotion.setEndDate(LocalDate.now().plusDays(7));
        promotionService.createPromotion(promotion, product, promotion.getEndDate().toString(), promotion.getBeginDate().toString());

        // Verified promotion is created
        assertNotNull(promotion.getId());
        assertEquals(promotion.getProduct().getId(), product.getId());
        assertEquals(promotion.getPercentage(), 20);
        assertEquals(promotion.getBeginDate(), LocalDate.now());
        assertEquals(promotion.getEndDate(), LocalDate.now().plusDays(7));
    }

    @Test
    public void testCreatePromotionRedirect() throws Exception {
        // Given
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        Promotion promotion = new Promotion();
        promotion.setPercentage(10);

        String endDate = "2023-05-31";
        String beginDate = "2023-05-01";

        doNothing().when(service).createPromotion(promotion, product, endDate, beginDate);

        MockHttpServletRequest request = new MockHttpServletRequest();

        // When
        String result = controller.createPromotion(promotion, new BeanPropertyBindingResult(promotion, "promotion"),
                product, request, model);

        // Then
        assertEquals("redirect:/admin/product", result);
    }

    @Test
    public void testDeletePromotion(){
        Long id = 1L;
        doNothing().when(service).deletePromotion(id);

        String result = controller.deletePromotion(id);

        verify(service, times(1)).deletePromotion(id);
        assertEquals("redirect:/admin/product", result);
    }


}
