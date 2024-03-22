package com.istad.demo.service;

import com.istad.demo.dto.ProductCreateRequest;
import com.istad.demo.dto.ProductDto;
import com.istad.demo.dto.ProductEditRequest;
import com.istad.demo.dto.ProductRespons;

import java.util.List;

public interface ProductService {
    void createNewProduct(ProductCreateRequest request);

    List<ProductDto> findProducts();
    void editProductByUuid(String uuid, ProductEditRequest request);

    List<ProductRespons> findProducts(String name, Boolean status);

    ProductRespons findProductById(Integer id);

    ProductRespons findProductByUuid(String uuid);

    void deleteProductByUuid(String uuid);

}
