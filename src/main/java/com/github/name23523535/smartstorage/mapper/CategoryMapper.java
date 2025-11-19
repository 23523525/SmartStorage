package com.github.name23523535.smartstorage.mapper;

import com.github.name23523535.smartstorage.dto.categoryDto.CategoryResponseDto;
import com.github.name23523535.smartstorage.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponseDto toCategoryResponseDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();

        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());

        return dto;
    }

}
