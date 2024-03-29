package com.example.mercadonabackend.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @SequenceGenerator(name="category_seq",
            sequenceName = "category_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @Column(name = "category_id", updatable = false)
    private Long id;

    @Column(name = "name")
    @Size(min = 1, max = 50)
    private String name;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    // constructor

    public Category(String name) {
        this.name = name;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters and setters

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}