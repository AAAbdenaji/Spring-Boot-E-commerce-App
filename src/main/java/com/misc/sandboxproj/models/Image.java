package com.misc.sandboxproj.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String img_name;
    private String img_url;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    @JsonIgnore
    private Product product;
}
