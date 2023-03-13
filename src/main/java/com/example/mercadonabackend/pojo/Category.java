package com.example.mercadonabackend.pojo;

import javax.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Category implements Serializable {
    @Id
    @SequenceGenerator(name="category_seq",
            sequenceName = "category_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @Column(name = "category_id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {

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