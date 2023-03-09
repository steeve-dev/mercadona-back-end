package com.example.mercadonabackend.Service.impl;

import com.example.mercadonabackend.Service.ProductService;
import com.example.mercadonabackend.pojo.Product;
import com.example.mercadonabackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        if (verifId(id)) {
            Optional<Product> productOptional = productRepository.findById(id);
            return productOptional.orElse(null);
        }
        else {
            return null;
        }
    }

    @Override
    public void updateProduct(Long productId, Product product) {
        this.deleteProduct(productId);
        productRepository.save(product);

    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private boolean verifId(Long id){
        return id != null;
    }
}
