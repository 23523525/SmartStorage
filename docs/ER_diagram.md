## 2.7. ER диаграмма

Сущности и атрибуты:

1. CATEGORY
    - id (PK, BIGSERIAL)
    - name (NOT NULL, UNIQUE)
    - description (TEXT, NULL)

2. ITEM
    - id (PK, BIGSERIAL)
    - name (NOT NULL)
    - description (TEXT)
    - count (INTEGER NOT NULL, по умолчанию 0)
    - category_id (FK → CATEGORY.id)

3. MOVEMENT
    - id (PK, BIGSERIAL)
    - item_id (FK → ITEM.id, NOT NULL, ON DELETE CASCADE)
    - type (VARCHAR(20), NOT NULL, значения ‘IN’/‘OUT’)
    - amount (INTEGER NOT NULL)
    - created_at (TIMESTAMPTZ NOT NULL, DEFAULT NOW())
    - comment (TEXT, NULL)

Связи:

- CATEGORY (1) — (N) ITEM  
  Одна категория может содержать множество товаров; каждый товар относится к одной категории.

- ITEM (1) — (N) MOVEMENT  
  Один товар может иметь множество записей в таблице движений; каждое движение относится к одному товару.

Комментарий:  
ER-диаграмма фиксирует физическую структуру базы данных PostgreSQL, на основе которой работают JPA-сущности и сервисы приложения.