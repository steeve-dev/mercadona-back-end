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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_PROMOTION)
public class PromotionWs {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProductService productService;

    @PostMapping("/post/{id}")
    public String createPromotion(@Valid Promotion promotion, BindingResult result, @PathVariable(name = "id") Product product, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        // checking if result has an error
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
        // return product management page and create new promotion
        String endDate = request.getParameter("dateEnd");
        String beginDate = request.getParameter("dateBegin");
        promotionService.createPromotion(promotion, product, endDate, beginDate);
        redirectAttributes.addFlashAttribute("successMessage", "La promotion à été ajouté avec succès !");
        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable(name = "id")Long id, RedirectAttributes redirectAttributes){
        promotionService.deletePromotion(id);
        redirectAttributes.addFlashAttribute("successMessage", "La promotion à été supprimé avec succès !");
        return "redirect:/admin/product";
    }
}
