package com.istad.demo.service;

import com.istad.demo.dto.ProductCreateRequest;
import com.istad.demo.dto.ProductDto;
import com.istad.demo.dto.ProductEditRequest;
import com.istad.demo.dto.ProductRespons;
import com.istad.demo.modol.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final List<Product> products;

    public ProductServiceImpl() {
        products = new ArrayList<>();

        Product p1 = new Product();
        p1.setId(1);
        p1.setUuid(UUID.randomUUID().toString());
        p1.setName("Iphone 5 pro");
        p1.setQty(10);
        p1.setPrice(1.055);
        p1.setDate(LocalDate.now());
        p1.setStatus(true);
        products.add(p1);

        Product p2 = new Product();
        p2.setId(2);
        p2.setUuid(UUID.randomUUID().toString());
        p2.setName("Iphone 5 pro Max");
        p2.setQty(10);
        p2.setPrice(1.300);
        p2.setDate(LocalDate.now());
        p2.setStatus(true);
        products.add(p2);
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.name());
        newProduct.setQty(request.qty());
        newProduct.setPrice(request.price());
        newProduct.setUuid(UUID.randomUUID().toString());
        newProduct.setDate(LocalDate.now());
        newProduct.setStatus(true);
        products.add(newProduct);
    }

    @Override
    public List<ProductDto> findProducts() {
        return products
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
    public void editProductByUuid(String uuid, ProductEditRequest request) {
        products.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .findFirst()
                .ifPresent(product -> {
                    product.setName(request.name());
                    product.setPrice(request.price());
                });
    }

    @Override
    public List<ProductRespons> findProducts(String name, Boolean status) {
        return null;
    }

    @Override
    public ProductRespons findProductById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id) &&
                        Objects.equals(product.getStatus(), true))
                .map(product -> new ProductRespons(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public ProductRespons findProductByUuid(String uuid) {
        return products.stream()
                .filter(product -> product.getUuid().equals(uuid) &&
                        Objects.equals(product.getStatus(), true))
                .map(product -> new ProductRespons(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void deleteProductByUuid(String uuid) {
        products.removeIf(product -> product.getUuid().equals(uuid));
    }

}
