package com.example.mercadonabackend.pojo;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Promotion implements Serializable {
    @Id
    @SequenceGenerator(name="promotion_seq",
            sequenceName = "promotion_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_seq")
    @Column(name = "promotion_id", updatable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "begin_date")
    private Date beginDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "percentage")
    private Integer percentage;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private Product product;

    public Promotion(Long id, Date beginDate, Date endDate, Integer percentage, Product product) {
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}