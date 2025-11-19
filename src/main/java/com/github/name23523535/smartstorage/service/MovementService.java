package com.github.name23523535.smartstorage.service;

import com.github.name23523535.smartstorage.dto.movementDto.AddStockDto;
import com.github.name23523535.smartstorage.dto.movementDto.RemoveStockDto;
import com.github.name23523535.smartstorage.entity.Item;
import com.github.name23523535.smartstorage.entity.Movement;
import com.github.name23523535.smartstorage.exception.NotEnoughStockException;
import com.github.name23523535.smartstorage.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovementService {

    private final MovementRepository movementRepository;
    private final ItemService itemService;

    public List<Movement> getAll() {
        return movementRepository.findAll();
    }

    public List<Movement> getByItemId(Long itemId) {
        return movementRepository.findByItemId(itemId);
    }

    public Movement addStock(Long itemId, AddStockDto dto) {
        Item item = itemService.getById(itemId);

        item.setCount(item.getCount() + dto.getAmount());

        itemService.save(item);

        Movement movement = new Movement();
        movement.setItem(item);
        movement.setAmount(dto.getAmount());
        movement.setType(Movement.Type.IN);
        movement.setComment(dto.getComment());

        return movementRepository.save(movement);
    }

    public Movement removeStock(Long itemId, RemoveStockDto dto) {
        Item item = itemService.getById(itemId);

        if (item.getCount() < dto.getAmount()) {
            throw new NotEnoughStockException("Not enough stock to remove " + dto.getAmount());
        }

        item.setCount(item.getCount() - dto.getAmount());

        itemService.save(item);

        Movement movement = new Movement();
        movement.setItem(item);
        movement.setAmount(dto.getAmount());
        movement.setType(Movement.Type.OUT);
        movement.setComment(dto.getComment());

        return movementRepository.save(movement);
    }
}
