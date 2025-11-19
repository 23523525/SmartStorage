package com.github.name23523535.smartstorage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "movement")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Enumerated(EnumType.STRING) // Сохраняет значения как "IN" или "OUT"
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private Integer amount;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(columnDefinition = "text")
    private String comment;

    public enum Type {
        IN, OUT
    }
}
