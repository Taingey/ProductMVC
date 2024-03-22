package com.istad.demo.controller;

import com.istad.demo.dto.CategoryResponse;
import com.istad.demo.modol.Product;
import com.istad.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {

    private final CategoryService categoryService;

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Categories not found",
                    content = @Content)
    })
//    @GetMapping("/simple")
//    public Map<String, Object> findCategories() {
//        Map<String, Object> data = new HashMap<>();
//        data.put("Message", "Categories Have been found successfully");
//        data.put("data", List.of("Education", "Entertainment"));
//        return data;
//    }

    @GetMapping
    public List<CategoryResponse> findAllCategories() {
        return categoryService.findCategoryCart();
    }
}
