package com.istad.demo.controller;

import com.istad.demo.dto.ProductCreateRequest;
import com.istad.demo.dto.ProductEditRequest;
import com.istad.demo.dto.ProductRespons;
import com.istad.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class Controller {
    private final ProductService productService;


    @GetMapping
    public ResponseEntity<?> getProducts() {
        Map<String, Object> data = Map.of("products", productService.findProducts());
        return ResponseEntity.accepted().body(data);
    }

    @PutMapping("/uuid/{uuid}")
    public void editProductByUuid(@PathVariable String uuid,
                                  @RequestBody ProductEditRequest request) {
        productService.editProductByUuid(uuid, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNewProduct(@Valid @RequestBody ProductCreateRequest request) {
        System.out.println("REQUEST: " + request);
        productService.createNewProduct(request);
    }

    @GetMapping("/{id}")
    public Map<String, Object> findProductById(@PathVariable Integer id) {
        ProductRespons product = productService.findProductById(id);
        return Map.of("message", "Product has been found", "data", product);
    }

    @GetMapping("/uuid/{uuid}")
    public Map<String, Object> findProductByUuid(@PathVariable String uuid) {
        ProductRespons product = productService.findProductByUuid(uuid);
        return Map.of("message", "Product has been found", "data", product);
    }

    @DeleteMapping("/uuid/{uuid}")
    public void deleteProductByUuid(@PathVariable String uuid) {
        productService.deleteProductByUuid(uuid);
    }
}