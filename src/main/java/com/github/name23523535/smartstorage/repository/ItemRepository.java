package com.github.name23523535.smartstorage.repository;

import com.github.name23523535.smartstorage.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategoryId(Long categoryId);

    List<Item> findByNameContainingIgnoreCase(String q);
}
