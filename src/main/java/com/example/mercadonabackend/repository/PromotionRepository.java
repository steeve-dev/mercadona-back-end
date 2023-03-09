package com.example.mercadonabackend.repository;


import com.example.mercadonabackend.pojo.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
