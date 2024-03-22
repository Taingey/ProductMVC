package com.istad.demo.service;

import com.istad.demo.dto.CategoryEditRequest;
import com.istad.demo.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findCategoryCart();
    void categoriesEdit(String uuid ,CategoryEditRequest request);
}
