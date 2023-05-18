package com.example.mercadonabackend.api;

import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.Service.PromotionService;
import com.example.mercadonabackend.pojo.Product;
import com.example.mercadonabackend.pojo.Promotion;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.stream.Collectors;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_PROMOTION)
public class PromotionWs {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProductService productService;

    @PostMapping("/post/{id}")
    public String createPromotion(@Valid Promotion promotion, BindingResult result, @PathVariable(name = "id") Product product, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("; "));
            model.addAttribute("errorMessage", errorMessage);
            Promotion newPromotion = new Promotion();
            model.addAttribute("promotion", newPromotion);
            model.addAttribute("product", productService.getProductById(product.getId()));
            return "adminAddPromotion";
        }
        String endDate = request.getParameter("dateEnd");
        String beginDate = request.getParameter("dateBegin");
        promotionService.createPromotion(promotion, product, endDate, beginDate);
        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable(name = "id")Long id){
        promotionService.deletePromotion(id);
        return "redirect:/admin/product";
    }
}
