package com.example.mercadonabackend.api;


import com.example.mercadonabackend.Service.CategoryService;
import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.pojo.Product;
import com.example.mercadonabackend.pojo.Promotion;
import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private Model model;

    @Mock
    private ProductService getProductService;

    @Autowired
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @Test
    public void testCategoryManagement() {
        // Setup
        ModelAndView expectedModelAndView = new ModelAndView();
        expectedModelAndView.setViewName("adminCategory.html");
        Category category1 = new Category();
        expectedModelAndView.addObject("newCategory", category1);

        List<Category> categoryList = new ArrayList<Category>();
        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Category 1");
        categoryList.add(category2);
        expectedModelAndView.addObject("categoryList", categoryList);

        when(categoryService.getAllCategory()).thenReturn(categoryList);

        // Execution
        ModelAndView actualModelAndView = adminController.categoryManagement();

        // Verification
        assertEquals(expectedModelAndView.getViewName(), actualModelAndView.getViewName());
        assertEquals(expectedModelAndView.getModel().size(), actualModelAndView.getModel().size());
        assertEquals(expectedModelAndView.getModel().get("categoryList"), actualModelAndView.getModel().get("categoryList"));
        assertEquals(expectedModelAndView.getModel().get("categoryList"), categoryList);
        verify(categoryService, times(1)).getAllCategory();
    }

    @Test
    public void testCreatePromotion() {
        productService = mock(ProductService.class);
        categoryService = mock(CategoryService.class);
        adminController = new AdminController(categoryService, productService);
        // Mock data
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        Promotion promotion = new Promotion();
        promotion.setId(1L);
        ModelAndView expectedModelAndView = new ModelAndView("adminAddPromotion.html");
        expectedModelAndView.addObject("promotion", promotion);
        expectedModelAndView.addObject("product", product);
        // Mock service methods
        when(productService.getProductById(product.getId())).thenReturn(product);

        // Test method
        ModelAndView actualModelAndView = adminController.createPromotion(productId);

        // Asserts
        assertEquals(expectedModelAndView.getViewName(), actualModelAndView.getViewName());
        assertEquals(expectedModelAndView.getModel().get("product"), actualModelAndView.getModel().get("product"));
    }

    @Test
    public void testGetAdminData(){

        ModelAndView modelAndView = adminController.getAdminData();
        assertEquals("admin.html", modelAndView.getViewName());
    }

    @Test
    public void testNewProduct(){
        // Mock categoryService
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Category 1"));
        categories.add(new Category("Category 2"));
        when(categoryService.getAllCategory()).thenReturn(categories);

        // Mock ModelAndView
        ModelAndView modelAndView = new ModelAndView("adminNewProduct.html");
        modelAndView.addObject("categoryList", categories);
        Product product = new Product();
        modelAndView.addObject("product", product);

        // Mock controller
        AdminController controller = new AdminController(categoryService, productService);

        // When
        ModelAndView result = controller.newProduct();

        // Then
        assertEquals(modelAndView.getViewName(), result.getViewName());
        assertEquals(modelAndView.getModel().size(), result.getModel().size());
        assertEquals(modelAndView.getModel().get("categoryList"), result.getModel().get("categoryList"));
        assertEquals(modelAndView.getModel().get("product").getClass(), result.getModel().get("product").getClass());
    }

    @Test
    public void testProductManagement() {
        // Given
        List<Product> productList = Arrays.asList(
                new Product("Product 1", "Description 1", "image1.jpg",10.0f,  new Category("Category 1")),
                new Product("Product 2", "Description 2", "image2.jpg",20.0f,  new Category("Category 2"))
        );
        List<Category> categoryList = Arrays.asList(
                new Category("Category 1"),
                new Category("Category 2")
        );
        when(getProductService.getAllProduct()).thenReturn(productList);
        when(categoryService.getAllCategory()).thenReturn(categoryList);

        // When
        ModelAndView result = adminController.productManagement();

        // Then
        verify(getProductService, times(1)).getAllProduct();
        verify(categoryService, times(1)).getAllCategory();
        assertEquals("adminProduct.html", result.getViewName());
        List<Product> resultProducts = (List<Product>) result.getModel().get("products");
        assertEquals(productList.size(), resultProducts.size());
        assertEquals(productList.get(0).getName(), resultProducts.get(0).getName());
        assertEquals(productList.get(1).getName(), resultProducts.get(1).getName());
        List<Category> resultCategories = (List<Category>) result.getModel().get("categoryList");
        assertEquals(categoryList.size(), resultCategories.size());
        assertEquals(categoryList.get(0).getName(), resultCategories.get(0).getName());
        assertEquals(categoryList.get(1).getName(), resultCategories.get(1).getName());
    }

    @Test
    public void testUpdateCategoryForm(){
        Category category = new Category();
        category.setName("category name");
        when(categoryService.getCategoryById(category.getId())).thenReturn(category);
        String result = adminController.updateCategoryForm(category.getId(), model);

        verify(categoryService, times(1)).getCategoryById(category.getId());
        assertEquals("components/admin/categoryUpdateForm.html", result);
    }

    @Test
    public void testProductUpdate() {
        // Given
        Long id = 1L;
        Product product = new Product();
        Category category = new Category();
        product.setId(id);
        product.setName("Product 1");
        product.setPrice(100.0f);
        product.setCategory(category);
        when(getProductService.getProductById(id)).thenReturn(product);
        when(categoryService.getAllCategory()).thenReturn(List.of(category));

        // When
        ModelAndView modelAndView = adminController.productUpdate(id);

        // Then
        assertEquals("adminUpdateProduct.html", modelAndView.getViewName());
        Product resultProduct = (Product) modelAndView.getModel().get("product");
        assertEquals(id, resultProduct.getId());
        assertEquals("Product 1", resultProduct.getName());
        assertEquals(100.0, resultProduct.getPrice(), 0.0);
        List<Category> categoryList = (List<Category>) modelAndView.getModel().get("categoryList");
        assertEquals(List.of(category), categoryList);
    }

    @Test
    public void testProductManagementByPromotion() {
        // Given
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(getProductService.getAllProduct()).thenReturn(productList);
        List<Category> categoryList = Arrays.asList(new Category(), new Category());
        when(categoryService.getAllCategory()).thenReturn(categoryList);

        // When
        ModelAndView result = adminController.productManagementByPromotion();

        // Then
        verify(getProductService, times(1)).getAllProduct();
        verify(categoryService, times(1)).getAllCategory();
        assertEquals("adminProductByPromotion.html", result.getViewName());
        assertEquals(productList, result.getModel().get("products"));
        assertEquals(categoryList, result.getModel().get("categoryList"));
    }

    @Test
    public void testProductManagementByCategory() {
        // Given
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);
        when(getProductService.getProductByCategory(category)).thenReturn(products);

        // When
        ModelAndView modelAndView = adminController.productManagementByCategory(categoryId);

        // Then
        verify(categoryService, times(1)).getCategoryById(categoryId);
        verify(getProductService, times(1)).getProductByCategory(category);
        assertEquals("adminProduct.html", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("categoryList"));
        assertTrue(modelAndView.getModel().containsKey("products"));
    }

}

