package com.example.mercadonabackend.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "promotion")
public class Promotion implements Serializable {
    @Id
    @SequenceGenerator(name="promotion_seq",
            sequenceName = "promotion_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_seq")
    @Column(name = "promotion_id", updatable = false)
    private Long id;

    @Column(name = "begin_date")
    private LocalDate beginDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "percentage")
    @Min(value = 1, message = "La valeur doit être au minimum égale à 1")
    @Max(value = 100, message = "La valeur doit être au maximum égale à 100")
    private Integer percentage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    public Promotion(Long id, LocalDate beginDate, LocalDate endDate, Integer percentage, Product product) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.percentage = percentage;
        this.product = product;
    }
    public Promotion() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {

        this.endDate = endDate;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}