package com.example.mercadonabackend.repository;

import com.example.mercadonabackend.pojo.Category;
import com.example.mercadonabackend.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);

}

