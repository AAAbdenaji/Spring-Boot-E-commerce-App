package com.misc.sandboxproj.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.misc.sandboxproj.DTOs.ProductDTOs.ProductCreateDto;
import com.misc.sandboxproj.DTOs.ProductDTOs.ProductUpdateDto;
import com.misc.sandboxproj.Helpers.ImageHelper;
import com.misc.sandboxproj.Repositories.ImageRepository;
import com.misc.sandboxproj.Repositories.ProductRepository;
import com.misc.sandboxproj.execeptions.NotFoundException;
import com.misc.sandboxproj.execeptions.ValidationException;
import com.misc.sandboxproj.models.Image;
import com.misc.sandboxproj.models.Product;

import jakarta.transaction.Transactional;


@Service
public class ProductService {
    private final ProductRepository prodRepo;
    private final ImageRepository imgRepo;
    private final ImageHelper imgHelper;

    private final String UploadDir = "wwwroot/";

    public ProductService(ProductRepository prodRepo, ImageRepository imgRepo, ImageHelper imgHelper) 
    {
        this.prodRepo = prodRepo;
        this.imgRepo = imgRepo;
        this.imgHelper = imgHelper;
    }

    public Product CreateProduct(ProductCreateDto req, List<MultipartFile> images) 
    {
        Product p = new Product();

        p.setProdName(req.getProdName());
        p.setProdDesc(req.getProdDesc());
        p.setStock(req.getStock());
        p.setPrice(req.getPrice());

        Product Saved = prodRepo.save(p);

        if (images != null && !images.isEmpty())
            addImages(Saved.getProdId(), images);

        return Saved;
    }



    public void addImages(int productId, List<MultipartFile> files) {

        Product product = prodRepo.findById(productId)
                .orElseThrow();

        for (MultipartFile file : files) {
            
            imgHelper.validateImage(file);
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UploadDir + fileName);

            try {
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                throw new ValidationException(e.getMessage());
            }

            Image image = new Image();
            image.setImg_name(fileName);
            image.setImg_url(path.toString());
            image.setProduct(product);

            imgRepo.save(image);
        }
    }

    public List<Product> getAllProducts() {
        return prodRepo.findAll();
    }
    public Product getProductbyId(int Id)
    {
        return prodRepo.findById(Id)
            .orElseThrow(() -> new NotFoundException("Product not found")) ;
    }
    public List<Image> getImagesOfProduct(int prodID)
    {
        return imgRepo.findByProductProdId(prodID);
    }
    public Path getImagePath(Integer ProdId, Integer imageId) {

        Image img = imgRepo.findById(imageId)
                .orElseThrow();

        if (img.getProduct().getProdId() != ProdId) {
            throw new ValidationException("Image not part of product");
        }

        return Path.of(img.getImg_url());
    }

    @Transactional
    public void deleteProductById(Integer id) {

        Product product = prodRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Product not found"));

        for (Image img : product.getImages()) {
            try {
                Files.deleteIfExists(Path.of(img.getImg_url()));
            } catch (Exception e) {
                throw new ValidationException("Failed deleting image file: " + img.getImg_url());
            }
        }
        prodRepo.delete(product);
    }

    @Transactional
    public Product updateProductById(Integer id, ProductUpdateDto Dto)
    {
        Product p = prodRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Product not found"));
        if (Dto.getProdName() != null)
            p.setProdName(Dto.getProdName());
        if (Dto.getProdDesc() != null)
            p.setProdDesc(Dto.getProdDesc());
        if (Dto.getStock() != null)
            p.setStock(Dto.getStock());
        if (Dto.getPrice() != null)
            p.setPrice(Dto.getPrice());

        return prodRepo.save(p);
    }
}
