package com.joseph.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproduct;
    @Column(name = "nameproduct")
    private String nameproduct;
    @Column(name = "priceproduct")
    private Double priceproduct;
    @Column(name = "stockproduct")
    private Long stockproduct;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    public Product(Long idproduct, String nameproduct, Double priceproduct, Long stockproduct,
            Set<OrderItem> orderItems) {
        this.idproduct = idproduct;
        this.nameproduct = nameproduct;
        this.priceproduct = priceproduct;
        this.stockproduct = stockproduct;
        this.orderItems = orderItems;
    }

    public Product() {
    }

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public Double getPriceproduct() {
        return priceproduct;
    }

    public void setPriceproduct(Double priceproduct) {
        this.priceproduct = priceproduct;
    }

    public Long getStockproduct() {
        return stockproduct;
    }

    public void setStockproduct(Long stockproduct) {
        this.stockproduct = stockproduct;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
