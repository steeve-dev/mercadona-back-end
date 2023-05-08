package com.example.mercadonabackend.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @SequenceGenerator(name="product_seq",
            sequenceName = "product_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @Column(name = "product_id", updatable = false)
    private Long id;

    @Column(name = "name")
    @Size(min = 1, max = 60)
    private String name;

    @Column(name = "description", length = 1000)
    @Size(min = 1, max = 500)
    private String description;

    @Column(name = "image")
    @Size(min = 1, max = 150)
    private String imageLink;

    @Column(name = "price")
    @Digits(integer = 8, fraction = 2, message ="Le prix doit être un nombre décimal positif avec deux chiffres après la virgule.")
    @DecimalMin(value = "0.00", inclusive = true, message = "Le prix doit être un nombre décimal positif avec deux chiffres après la virgule.")
    private Float price;

    @Column(name = "promotion_price")
    private Float promotionPrice = null;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion = null;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String name, String description, String imageLink, Float price, Category category) {
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.price = price;
        this.category = category;
    }

    public Float getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Float promotionPrice) {
        this.promotionPrice = promotionPrice;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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