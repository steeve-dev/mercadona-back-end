package com.example.mercadonabackend.Service;

import com.example.mercadonabackend.pojo.Product;
import com.example.mercadonabackend.pojo.Promotion;

import java.util.List;

public interface PromotionService {

    List<Promotion> getAllPromotion();

    Promotion getPromotionById(Long id);

    void createPromotion(Promotion promotion, Product product, String endDate, String beginDate);

    void updatePromotion(Long promotionId, Promotion promotion);

    void deletePromotion(Long id);

}
