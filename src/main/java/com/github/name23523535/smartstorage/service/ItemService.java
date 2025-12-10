package com.github.name23523535.smartstorage.service;

import com.github.name23523535.smartstorage.dto.itemDto.CreateItemDto;
import com.github.name23523535.smartstorage.dto.itemDto.UpdateItemDto;
import com.github.name23523535.smartstorage.dto.movementDto.AddStockDto;
import com.github.name23523535.smartstorage.entity.Category;
import com.github.name23523535.smartstorage.entity.Item;
import com.github.name23523535.smartstorage.exception.NotFoundException;
import com.github.name23523535.smartstorage.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final QrCodeService qrCodeService;

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found: " + id));
    }

    public Item create(CreateItemDto dto) {
        Item item = new Item();
        Category category = categoryService.getById(dto.getCategoryId());

        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(category);
        item.setCount(dto.getCount());

        return save(item);
    }

    public void delete(Long id) {
        getById(id);
        itemRepository.deleteById(id);
    }

    public List<Item> getByCategory(Long categoryId) {
        return itemRepository.findByCategoryId(categoryId);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item update(Long id, UpdateItemDto dto) {
        Item item = getById(id);
        Category category = categoryService.getById(dto.getCategoryId());

        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(category);
        item.setCount(dto.getCount());

        return itemRepository.save(item);
    }

    public List<Item> search(String q) {
        return itemRepository.findByNameContainingIgnoreCase(q);
    }

    public String getQrCode(Long id) {
        Item item = getById(id);
        String payload = "Item:" + item.getId();
        return qrCodeService.generateQrCode(payload);
    }

    public int getCountByCategoryId(Long id) {
        return itemRepository.countByCategoryId(id);
    }
}
