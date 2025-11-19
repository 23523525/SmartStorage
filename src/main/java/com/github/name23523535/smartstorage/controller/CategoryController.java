package com.github.name23523535.smartstorage.controller;

import com.github.name23523535.smartstorage.dto.categoryDto.CategoryResponseDto;
import com.github.name23523535.smartstorage.dto.categoryDto.CreateCategoryDto;
import com.github.name23523535.smartstorage.dto.categoryDto.UpdateCategoryDto;
import com.github.name23523535.smartstorage.entity.Category;
import com.github.name23523535.smartstorage.mapper.CategoryMapper;
import com.github.name23523535.smartstorage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @GetMapping
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryService.getAll();

        return categories.stream()
                .map(categoryMapper::toCategoryResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getById(id);

        return categoryMapper.toCategoryResponseDto(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto createCategory(@RequestBody CreateCategoryDto dto) {
        Category category = categoryService.create(dto);

        return categoryMapper.toCategoryResponseDto(category);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryDto dto) {
        Category category = categoryService.update(id, dto);

        return categoryMapper.toCategoryResponseDto(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
