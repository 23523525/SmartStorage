package com.github.name23523535.smartstorage.mapper;

import com.github.name23523535.smartstorage.dto.movementDto.MovementRequestDto;
import com.github.name23523535.smartstorage.entity.Movement;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {

    public MovementRequestDto toMovementRequestDto(Movement movement) {
        MovementRequestDto dto = new MovementRequestDto();

        dto.setId(movement.getId());
        dto.setItemId(movement.getItem().getId());
        dto.setType(movement.getType().toString());
        dto.setAmount(movement.getAmount());
        dto.setComment(movement.getComment());
        dto.setCreatedAt(movement.getCreatedAt());

        return dto;
    }
}
