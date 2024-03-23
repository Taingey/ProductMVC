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
    public ResponseEntity<Map<String, Object>> getProducts() {
        Map<String, Object> data = Map.of("products", productService.findProducts());
        return ResponseEntity.ok(data);
    }

    @PutMapping("/uuid/{uuid}")
    public ProductRespons editProductByUuid(@PathVariable String uuid,
                                            @RequestBody @Valid ProductEditRequest request) {
        return productService.editProductByUuid(uuid, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNewProduct(@Valid @RequestBody ProductCreateRequest request) {
        productService.createNewProduct(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findProductById(@PathVariable Integer id) {
        ProductRespons product = productService.findProductById(id);
        return ResponseEntity.ok(Map.of("message", "Product has been found", "data", product));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<Map<String, Object>> findProductByUuid(@PathVariable String uuid) {
        ProductRespons product = productService.findProductByUuid(uuid);
        return ResponseEntity.ok(Map.of("message", "Product has been found", "data", product));
    }

    @DeleteMapping("/uuid/{uuid}")
    public void deleteProductByUuid(@PathVariable String uuid) {
        productService.deleteProductByUuid(uuid);
    }
}
