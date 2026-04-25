package com.misc.sandboxproj.DTOs.ProductDTOs;

import lombok.Data;

@Data
public class ProductCreateDto {
    private String prodName;
    private String prodDesc;
    private Integer stock;
    private double price;
}
