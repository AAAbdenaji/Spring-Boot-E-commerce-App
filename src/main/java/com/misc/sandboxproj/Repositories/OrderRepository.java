package com.misc.sandboxproj.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misc.sandboxproj.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(UUID id);
}
