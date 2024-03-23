package com.istad.demo.service;

import com.istad.demo.dto.CategoryRequest;
import com.istad.demo.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    void  deleteCategoryById(Integer id);
    List<CategoryResponse> findCategoryCart();
    CategoryResponse categoriesEdit(Integer id ,CategoryRequest request);

    CategoryResponse findCategoryByName(String name);
    CategoryResponse findCategoryByID(Integer id);

    void createdNewCategories(CategoryRequest response);

}
