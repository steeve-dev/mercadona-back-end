package com.example.mercadonabackend.repository;


import com.example.mercadonabackend.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
