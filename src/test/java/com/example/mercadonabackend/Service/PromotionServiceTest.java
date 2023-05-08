package com.example.mercadonabackend.Service;


import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.pojo.Product;
import com.example.mercadonabackend.pojo.Promotion;
import com.example.mercadonabackend.repository.ProductRepository;
import com.example.mercadonabackend.repository.PromotionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PromotionServiceTest {

    @MockBean
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionService promotionService;

    @Test
    public void testGetAllPromotion() {

        // Given
        Promotion promotion1 = new Promotion(1L, LocalDate.now(), LocalDate.now().plusDays(7), 50, new Product());
        Promotion promotion2 = new Promotion(2L, LocalDate.now(), LocalDate.now().plusDays(7), 35, new Product());
        List<Promotion> expectedPromotions = Arrays.asList(promotion1, promotion2);

        when(promotionRepository.findAll()).thenReturn(expectedPromotions);

        // When
        List<Promotion> actualPromotions = promotionService.getAllPromotion();

        // Then
        assertEquals(expectedPromotions, actualPromotions);
        verify(promotionRepository, times(1)).findAll();
    }

    @Test
    public void testGetPromotionById() {
        // Given
        Long promotionId = 1L;
        Promotion expectedPromotion = new Promotion(1L, LocalDate.now(), LocalDate.now().plusDays(7), 50, new Product());

        when(promotionRepository.findById(promotionId)).thenReturn(Optional.of(expectedPromotion));

        // When
        Promotion actualPromotion = promotionService.getPromotionById(promotionId);

        // Then
        verify(promotionRepository, times(1)).findById(promotionId);
        assertEquals(expectedPromotion, actualPromotion);
    }



}
