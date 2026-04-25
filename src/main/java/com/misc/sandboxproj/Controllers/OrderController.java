package com.misc.sandboxproj.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.misc.sandboxproj.DTOs.OrderDTOs.BuyNowRequest;
import com.misc.sandboxproj.Service.OrderService;
import com.misc.sandboxproj.models.Order;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    private final OrderService OrdServ;

    public OrderController(OrderService OrdServ) {
        this.OrdServ = OrdServ;
    }

    @PostMapping("/buy-now")
    @PreAuthorize("hasRole('USER')")
    public Order buyNow(@org.springframework.web.bind.annotation.RequestBody BuyNowRequest request)
    {
        System.out.println("Prodid: " + request.getProdId());
        return OrdServ.buyNow(request);
    }
}
