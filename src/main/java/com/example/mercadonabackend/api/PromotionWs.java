package com.example.mercadonabackend.api;

import com.example.mercadonabackend.Service.PromotionService;
import com.example.mercadonabackend.pojo.Promotion;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_PROMOTION)
public class PromotionWs {

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public List<Promotion> getAllPromotion(){

        return promotionService.getAllPromotion();
    }

    @GetMapping("{id}")
    public Promotion getPromotionById(@PathVariable(name = "id") Long id){
        return promotionService.getPromotionById(id);
    }

    @PostMapping
    public void createPromotion(Promotion promotion){
        promotionService.createPromotion(promotion);
    }

    @PutMapping("{id}")
    public void updatePromotion(@PathVariable(name = "id") Long id, @RequestBody Promotion promotion){
        promotionService.updatePromotion(id, promotion);
    }

    @DeleteMapping("{id}")
    public void deletePromotion(@PathVariable(name = "id")Long id){
        promotionService.deletePromotion(id);
    }

}
