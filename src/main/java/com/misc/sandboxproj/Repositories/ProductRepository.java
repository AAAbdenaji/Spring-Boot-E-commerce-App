package com.misc.sandboxproj.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misc.sandboxproj.models.Product;

public interface  ProductRepository extends JpaRepository<Product, Integer> {
    
}
