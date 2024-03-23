package com.istad.demo.service;

import com.istad.demo.Repository.CategoryRepository;
import com.istad.demo.dto.CategoryRequest;
import com.istad.demo.dto.CategoryResponse;
import com.istad.demo.modol.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponse> findCategoryCart() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryResponse(category.getName(),
                        category.getDescription()))
                .toList();
    }

    @Override
    public void deleteCategoryById(Integer id) {
        if (!categoryRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category has not been found!"
            );
        }
        else {
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public CategoryResponse categoriesEdit(Integer id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not been found!"
                ));

        category.setName(request.name());
        category.setDescription(request.description());

        categoryRepository.save(category);
        return this.findCategoryByID(id);
    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        return null;
    }

    @Override
    public CategoryResponse findCategoryByID(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found with id: " + id
                ));
        return new CategoryResponse(category.getName(), category.getDescription());
    }

    @Override
    public void createdNewCategories(CategoryRequest response) {

        if (categoryRepository.existsByName(response.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already existed!"
            );
        }

        Category category = new Category();
        category.setName(response.name().toLowerCase());
        category.setDescription(response.description());
        categoryRepository.save(category);
    }
}