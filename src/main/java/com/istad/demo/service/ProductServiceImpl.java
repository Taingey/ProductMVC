package com.istad.demo.service;

import com.istad.demo.Repository.ProdcutRepository;
import com.istad.demo.dto.ProductCreateRequest;
import com.istad.demo.dto.ProductDto;
import com.istad.demo.dto.ProductEditRequest;
import com.istad.demo.dto.ProductRespons;
import com.istad.demo.modol.Product;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProdcutRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProdcutRepository prodcutRepository) {
        this.productRepository = prodcutRepository;
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        if (productRepository.existsByName(request.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Product name already exists!"
            );
        }

        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        product.setUuid(UUID.randomUUID().toString());
        product.setDate(LocalDate.now());
        product.setStatus(true);
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> findProducts() {
        List<Product> productList = productRepository.findAll();
        return productList
                .stream()
                .filter(Product::getStatus)
                .map(product -> new ProductDto(
                        product.getUuid(),
                        product.getName(),
                        product.getQty(),
                        product.getPrice()
                ))
                .toList();
    }

    @Override
    public ProductRespons editProductByUuid(String uuid, ProductEditRequest request) {
        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with UUID: " + uuid
                ));

        product.setName(request.name());
        product.setQty(request.qty());
        product.setPrice(request.price());
        productRepository.save(product);
        return this.findProductByUuid(uuid);
    }

    @Override
    public ProductRespons findProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with id: " + id
                ));
        return new ProductRespons(product.getUuid(), product.getName(), product.getPrice(), product.getQty());
    }

    @Override
    public ProductRespons findProductByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with UUID: " + uuid
                ));

        if (!product.getStatus()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product with UUID: " + uuid + " is not available"
            );
        }
        return new ProductRespons(product.getUuid(), product.getName(), product.getPrice(), product.getQty());
    }

    @Override
    public void deleteProductByUuid(String uuid) {
        if (!productRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found with UUID: " + uuid
            );
        } else {
            productRepository.deleteByUuid(uuid);
        }
    }
}
