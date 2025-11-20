package com.github.name23523535.smartstorage.controller;

import com.github.name23523535.smartstorage.dto.itemDto.CreateItemDto;
import com.github.name23523535.smartstorage.dto.itemDto.ItemResponseDto;
import com.github.name23523535.smartstorage.dto.itemDto.UpdateItemDto;
import com.github.name23523535.smartstorage.entity.Item;
import com.github.name23523535.smartstorage.mapper.ItemMapper;
import com.github.name23523535.smartstorage.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping
    public List<ItemResponseDto> getAllItems() {
        List<Item> items = itemService.getAll();

        return items.stream()
                .map(itemMapper::toItemResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ItemResponseDto getItemById(@PathVariable Long id) {
        Item item = itemService.getById(id);

        return itemMapper.toItemResponseDto(item);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponseDto createItem(@RequestBody CreateItemDto dto) {

        Item item = itemService.create(dto);

        return itemMapper.toItemResponseDto(item);
    }

    @PutMapping("/{id}")
    public ItemResponseDto updateItem(@PathVariable Long id, @RequestBody UpdateItemDto dto) {
        Item item = itemService.update(id, dto);

        return itemMapper.toItemResponseDto(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id) {
        itemService.delete(id);
    }

    @GetMapping("/search")
    public List<ItemResponseDto> searchItem(@RequestParam String q) {
        List<Item> items = itemService.search(q);
        return items.stream()
                .map(itemMapper::toItemResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category/{categoryId}")
    public List<ItemResponseDto> getItemsByCategory(@PathVariable Long categoryId) {
        List<Item> items = itemService.getByCategory(categoryId);

        return items.stream()
                .map(itemMapper::toItemResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/qr/{id}")
    public String getQrCode(@PathVariable Long id) {
        return itemService.getQrCode(id);
    }
}
