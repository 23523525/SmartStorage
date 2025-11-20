package com.github.name23523535.smartstorage.service;

import com.github.name23523535.smartstorage.dto.categoryDto.CreateCategoryDto;
import com.github.name23523535.smartstorage.dto.categoryDto.UpdateCategoryDto;
import com.github.name23523535.smartstorage.entity.Category;
import com.github.name23523535.smartstorage.exception.NotFoundException;
import com.github.name23523535.smartstorage.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found: " + id));
    }

    public Category create(CreateCategoryDto dto) {
        Category category = new Category();

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return save(category);
    }

    public Category update(Long Id, UpdateCategoryDto dto) {
        Category existing = getById(Id);

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());

        return save(existing);
    }

    public void delete(Long id) {
        getById(id);
        categoryRepository.deleteById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
