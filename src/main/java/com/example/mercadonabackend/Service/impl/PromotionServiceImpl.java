package com.example.mercadonabackend.Service.impl;

import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.Service.PromotionService;
import com.example.mercadonabackend.pojo.Product;
import com.example.mercadonabackend.pojo.Promotion;
import com.example.mercadonabackend.repository.ProductRepository;
import com.example.mercadonabackend.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public List<Promotion> getAllPromotion() {

        return promotionRepository.findAll();
    }

    @Override
    public Promotion getPromotionById(Long id) {
        if (verifId(id)) {
            Optional<Promotion> promotionOptional = promotionRepository.findById(id);
            return promotionOptional.orElse(null);
        }
        else {
            return null;
        }
    }

    @Override
    public void createPromotion(Promotion promotion, Product product, String endDate, String beginDate) {
        promotion.setProduct(product);
        String date = "2023-03-24";
        String date2 = "2023-03-26";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endDateParse = LocalDate.parse(endDate, formatter);
        LocalDate beginDateParse = LocalDate.parse(beginDate, formatter);
        promotion.setEndDate(endDateParse);
        promotion.setBeginDate(beginDateParse);

        promotionRepository.save(promotion);
        product.setPromotion(promotion);
        int percentage = 100-promotion.getPercentage();
        float pricePromotion = product.getPrice()*percentage/100;
        product.setPromotionPrice(pricePromotion);
        productRepository.save(product);
    }

    @Override
    public void updatePromotion(Long promotionId, Promotion promotion) {
        this.deletePromotion(promotionId);
        promotionRepository.save(promotion);
    }

    @Override
    public void deletePromotion(Long id) {
        Product product = productService.getProductById(id);
        Promotion promotion = product.getPromotion();
        product.setPromotion(null);
        product.setPromotionPrice(null);
        promotionRepository.deleteById(promotion.getId());
        productRepository.save(product);
    }

    private boolean verifId(Long id){
        return id != 0;
    }
}
