package com.misc.sandboxproj.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.misc.sandboxproj.DTOs.ProductDTOs.ProductCreateDto;
import com.misc.sandboxproj.DTOs.ProductDTOs.ProductUpdateDto;
import com.misc.sandboxproj.Service.ProductService;
import com.misc.sandboxproj.models.Image;
import com.misc.sandboxproj.models.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService prodServ;
    private final ObjectMapper objMapper;

    public ProductController(ProductService prodServ, ObjectMapper objMapper) 
    {
        this.prodServ = prodServ;
        this.objMapper = objMapper;
    }

    @PostMapping(consumes= "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public Product createProduct(
        @RequestPart("product") String ProductJson,
        @RequestPart(value="images", required=false) List<MultipartFile> images
    ) throws Exception
    {
        ProductCreateDto dto = objMapper.readValue(ProductJson, ProductCreateDto.class);

        return prodServ.CreateProduct(dto, images);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return prodServ.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProductbyId(@PathVariable int id)
    {
        return prodServ.getProductbyId(id);
    }

    @GetMapping("/{id}/images")
    public List<Image> getProductImages(@PathVariable Integer id) {
        return prodServ.getImagesOfProduct(id);
    }
    
    @GetMapping("/{productId}/images/{imageId}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable Integer productId,
            @PathVariable Integer imageId
    ) {
        return prodServ.getImage(productId, imageId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable Integer id)
    {
        prodServ.deleteProductById(id);
    } 
    

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(
        @PathVariable Integer id,
        @RequestBody ProductUpdateDto dto
        ) 
    {

        Product updated = prodServ.updateProductById(id, dto);

        return ResponseEntity.ok(updated);
    }
}
