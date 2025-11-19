package com.github.name23523535.smartstorage.repository;

import com.github.name23523535.smartstorage.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByItemId(Long itemId);
}
