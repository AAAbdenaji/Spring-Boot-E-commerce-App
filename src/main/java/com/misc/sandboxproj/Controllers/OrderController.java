package com.misc.sandboxproj.Controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misc.sandboxproj.DTOs.OrderDTOs.BuyNowRequest;
import com.misc.sandboxproj.Service.OrderService;
import com.misc.sandboxproj.models.Order;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/order")
public class OrderController {
    
    private final OrderService OrdServ;

    public OrderController(OrderService OrdServ) {
        this.OrdServ = OrdServ;
    }

    @PostMapping("/buy-now")
    @PreAuthorize("hasRole('USER')")
    public Order buyNow(@Valid @RequestBody BuyNowRequest request)
    {
        System.out.println("Prodid: " + request.getProdId());
        return OrdServ.buyNow(request);
    }

    @DeleteMapping("/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(@PathVariable int Id)
    {
        OrdServ.deleteOrderbyId(Id);
    } 

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public List<Order> getOrdersbyUser()
    {
        return OrdServ.findOrdersByUser();
    }
}
