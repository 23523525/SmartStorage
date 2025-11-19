package com.github.name23523535.smartstorage.controller;

import com.github.name23523535.smartstorage.dto.movementDto.AddStockDto;
import com.github.name23523535.smartstorage.dto.movementDto.MovementRequestDto;
import com.github.name23523535.smartstorage.dto.movementDto.RemoveStockDto;
import com.github.name23523535.smartstorage.entity.Movement;
import com.github.name23523535.smartstorage.mapper.MovementMapper;
import com.github.name23523535.smartstorage.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movements")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;
    private final MovementMapper movementMapper;

    @GetMapping
    public List<MovementRequestDto> getAllMovements() {
        List<Movement> movements = movementService.getAll();

        return movements.stream()
                .map(movementMapper::toMovementRequestDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-item/{itemId}")
    public List<MovementRequestDto> getMovementsByItemId(@PathVariable Long itemId) {
        List<Movement> movements = movementService.getByItemId(itemId);

        return movements.stream()
                .map(movementMapper::toMovementRequestDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/add-stock/{itemId}")
    public MovementRequestDto addStock(@PathVariable Long itemId, @RequestBody AddStockDto dto) {
        Movement movement = movementService.addStock(itemId, dto);

        return movementMapper.toMovementRequestDto(movement);
    }

    @PostMapping("/remove-stock/{itemId}")
    public MovementRequestDto removeStock(@PathVariable Long itemId, @RequestBody RemoveStockDto dto) {
        Movement movement = movementService.removeStock(itemId, dto);

        return movementMapper.toMovementRequestDto(movement);
    }
}
