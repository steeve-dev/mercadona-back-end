package com.example.mercadonabackend.Service;

import com.example.mercadonabackend.pojo.Promotion;

import java.util.List;

public interface PromotionService {

    List<Promotion> getAllPromotion();

    Promotion getPromotionById();

    void createPromotion(Promotion promotion);

    void updatePromotion(Long promotionId, Promotion promotion);

    void deletePromotion(Long id);




}
