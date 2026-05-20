package com.misc.sandboxproj.Service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.misc.sandboxproj.DTOs.OrderDTOs.BuyNowRequest;
import com.misc.sandboxproj.Helpers.CustomUserDetails;
import com.misc.sandboxproj.Repositories.OrderRepository;
import com.misc.sandboxproj.Repositories.ProductRepository;
import com.misc.sandboxproj.execeptions.NotFoundException;
import com.misc.sandboxproj.execeptions.ValidationException;
import com.misc.sandboxproj.models.Order;
import com.misc.sandboxproj.models.Product;
import com.misc.sandboxproj.models.User;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    private final ProductRepository ProdRepo;
    private final OrderRepository OrderRepo;

    public OrderService(ProductRepository ProdRepo, OrderRepository OrderRepo)
    {
        this.OrderRepo = OrderRepo;
        this.ProdRepo = ProdRepo;
    }


    @Transactional
    public Order buyNow(BuyNowRequest req)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        User user = userDetails.getUser();

        Product product = ProdRepo.findById(req.getProdId()).orElseThrow(() -> new NotFoundException("Product not found."));

        if (product.getStock() < req.getQuantity())
        throw new ValidationException("Out of stock");

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(req.getQuantity());
        order.setTotal(product.getPrice() * req.getQuantity());

        product.setStock(product.getStock() - req.getQuantity());

        ProdRepo.save(product);
        return OrderRepo.save(order);
    }

    public List<Order> findOrdersByUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        User user = userDetails.getUser();

        return OrderRepo.findByUserId(user.getId());
    }

    @Transactional
    public void deleteOrderbyId(int orderid) 
    {
        Order o = OrderRepo.findById(orderid)
            .orElseThrow(() -> new NotFoundException("Order Not Found"));
        OrderRepo.delete(o);
    }
}
