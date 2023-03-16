package com.example.mercadonabackend.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Product implements Serializable {
    @Id
    @SequenceGenerator(name="product_seq",
            sequenceName = "product_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @Column(name = "product_id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price")
    private Float price;

    @OneToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion = null;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(Long id, String name, String description, Float price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Product(Long id, String name, String description, Float price, Promotion promotion, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.promotion = promotion;
        this.category = category;
    }

    public Product(Long id, Promotion promotion, Category category) {
        this.id = id;
        this.promotion = promotion;
        this.category = category;
    }

    public Product(){

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}