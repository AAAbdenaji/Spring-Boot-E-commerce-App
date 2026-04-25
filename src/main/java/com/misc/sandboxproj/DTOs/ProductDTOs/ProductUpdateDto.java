package com.misc.sandboxproj.DTOs.ProductDTOs;

import lombok.Data;

@Data
public class ProductUpdateDto {
    private String prodName;
    private String prodDesc;
    private Integer stock;
    private Double price;
}

