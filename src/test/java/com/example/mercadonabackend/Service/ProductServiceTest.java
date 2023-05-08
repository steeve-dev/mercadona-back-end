package com.example.mercadonabackend.Service;

import com.example.mercadonabackend.pojo.*;
import com.example.mercadonabackend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;


    @Test
    void testGetAllProduct() {
        // Créer une liste de produits de test
        List<Product> productList = Arrays.asList(
                new Product("Produit 1", "ceci est un produit", "image.jpg", 1.00f,new Category()),
                new Product("Produit 2", "ceci est un produit", "image.jpg", 1.00f,new Category()),
                new Product("Produit 3", "ceci est un produit", "image.jpg", 1.00f,new Category())
        );

        // Configurer le mock du repository pour retourner la liste de produits de test
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        // Appeler la méthode à tester
        List<Product> result = productService.getAllProduct();

        // Vérifier que la méthode a bien retourné la liste de produits de test
        assertEquals(productList, result);
    }


    @Test
    public void testGetCategory() {
        // Given
        Category category = new Category("categoryName");
        Product product = new Product("productName", "productDescription", "productImageLink", 10.0f, category);

        // When
        Category resultCategory = product.getCategory();

        // Then
        assertEquals(category, resultCategory);
    }

    @Test void testGetPromotion(){
        Promotion promotion = new Promotion();
        Product product = new Product("productName", "productDescription", "productImageLink", 10.0f, new Category());
        product.setPromotion(promotion);
        // When
        Promotion productPromotion = product.getPromotion();

        // Then
        assertEquals(promotion, productPromotion);
    }

    @Test
    public void testGetProductById() {
        // Given
        Long productId = 1L;
        Product expectedProduct = new Product("Test product", "ceci est un produit", "/image.jpg" ,100.00f, new Category());
        expectedProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        // When
        Product actualProduct = productService.getProductById(productId);

        // Then
        verify(productRepository, times(1)).findById(productId);
        assertEquals(expectedProduct, actualProduct);
    }
}
