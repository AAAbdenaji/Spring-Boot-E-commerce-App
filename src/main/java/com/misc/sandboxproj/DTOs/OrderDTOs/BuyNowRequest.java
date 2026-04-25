package com.misc.sandboxproj.DTOs.OrderDTOs;

import lombok.Data;

@Data
public class BuyNowRequest {
    private int prodId;
    private int quantity;
}
