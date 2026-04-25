package com.misc.sandboxproj.models;

import java.time.LocalDateTime;

import com.misc.sandboxproj.Helpers.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name= "user_id", nullable=false)
    private User user;

    private int quantity;
    
    private double total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;

    @PrePersist
    public void PrePersist()
    {
        createdAt = LocalDateTime.now();
        status = OrderStatus.PENDING_PAYMENT;
    }
}
