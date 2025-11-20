package com.github.name23523535.smartstorage.mapper;

import com.github.name23523535.smartstorage.dto.itemDto.ItemResponseDto;
import com.github.name23523535.smartstorage.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemResponseDto toItemResponseDto(Item item) {
        ItemResponseDto dto = new ItemResponseDto();

        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setCount(item.getCount());
        dto.setCategoryId(item.getCategory().getId());
        dto.setCategoryName(item.getCategory().getName());

        return dto;
    }
}
