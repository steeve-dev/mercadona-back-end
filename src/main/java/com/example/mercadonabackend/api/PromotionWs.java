package com.example.mercadonabackend.api;

import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.Service.PromotionService;
import com.example.mercadonabackend.pojo.Product;
import com.example.mercadonabackend.pojo.Promotion;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_PROMOTION)
public class PromotionWs {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProductService productService;

    @PostMapping("/post/{id}")
    public String createPromotion(Promotion promotion, @PathVariable (name = "id") Product product, HttpServletRequest request){
        String endDate = request.getParameter("dateEnd");
        String beginDate = request.getParameter("dateBegin");
        promotionService.createPromotion(promotion, product, endDate, beginDate);
        return "redirect:/admin/product";
    }

    @PutMapping("{id}")
    public void updatePromotion(@PathVariable(name = "id") Long id, Promotion promotion){
        promotionService.updatePromotion(id, promotion);
    }

    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable(name = "id")Long id){
        promotionService.deletePromotion(id);
        return "redirect:/admin/product";
    }
}
