package com.joseph.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder")
    private Long idorder;

    @Column(name = "dateorder", nullable = false)
    private LocalDate dateorder = LocalDate.now();
    @Column(name = "totalorder")
    private Double totalorder;
    @Enumerated(EnumType.STRING)
    @Column(name = "statusorder", nullable = false)
    private Status statusorder = Status.INPROGRESS;

    @ManyToOne
    @JoinColumn(name = "idclient")
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order() {
    }

    public Order(Long idorder, LocalDate dateorder, Double totalorder, Status statusorder, Client client,
            Set<OrderItem> orderItems) {
        this.idorder = idorder;
        this.dateorder = dateorder;
        this.totalorder = totalorder;
        this.statusorder = statusorder;
        this.client = client;
        this.orderItems = orderItems;
    }

    public Long getIdorder() {
        return idorder;
    }

    public void setIdorder(Long idorder) {
        this.idorder = idorder;
    }

    public LocalDate getDateorder() {
        return dateorder;
    }

    public void setDateorder(LocalDate dateorder) {
        this.dateorder = dateorder;
    }

    public Double getTotalorder() {
        return totalorder;
    }

    public void setTotalorder(Double totalorder) {
        this.totalorder = totalorder;
    }

    public Status getStatusorder() {
        return statusorder;
    }

    public void setStatusorder(Status statusorder) {
        this.statusorder = statusorder;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}