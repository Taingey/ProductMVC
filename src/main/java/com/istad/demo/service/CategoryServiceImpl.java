package com.istad.demo.service;

import com.istad.demo.Repository.CategoryRepository;
import com.istad.demo.dto.CategoryEditRequest;
import com.istad.demo.dto.CategoryResponse;
import com.istad.demo.modol.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final List<CategoryRepository> categoryRepository;

    public CategoryServiceImpl(List<CategoryRepository> categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponse> findCategoryCart() {
        List<Category> categories = categoryRepository.getFirst().findAll();
        return categories.stream()
                .map(category -> new CategoryResponse(category.getName(),
                        category.getDescription()))
                .toList();
    }

    @Override
    public void categoriesEdit(String uuid, CategoryEditRequest request) {
        categoryRepository.stream().filter(categoryRepository1 -> categoryRepository1.findAll().getFirst().getUuid().equals(uuid))
                .map()
    }

}
