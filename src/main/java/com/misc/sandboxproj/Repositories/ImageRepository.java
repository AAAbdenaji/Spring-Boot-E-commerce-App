package com.misc.sandboxproj.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misc.sandboxproj.models.Image;

public interface  ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByProductProdId(Integer prodId);
}
