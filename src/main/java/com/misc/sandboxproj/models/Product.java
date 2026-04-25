package com.misc.sandboxproj.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int prodId;
    private String prodName;
    private String prodDesc;
    private int stock;
    private double price;

    @OneToMany(mappedBy= "product", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<Image> images;
}
