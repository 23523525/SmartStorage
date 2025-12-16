## 2.6. Модели в нотации UML (три диаграммы)

### 2.6.1. UML диаграмма классов (структурная)

Основные классы:

- Category
    - id: Long
    - name: String
    - description: String
    - items: List<Item>

- Item
    - id: Long
    - name: String
    - description: String
    - count: Integer
    - category: Category

- Movement
    - id: Long
    - item: Item
    - type: Type (IN/OUT)
    - amount: Integer
    - createdAt: OffsetDateTime
    - comment: String

- CategoryService, ItemService, MovementService, QrCodeService — классы бизнес-логики.
- CategoryController, ItemController, MovementController, QrCodeController — контроллеры REST-слоя.

Связи:

- Category 1 — * Item (одна категория содержит множество товаров).
- Item 1 — * Movement (каждый товар имеет множество движений).
- Контроллеры зависят от соответствующих сервисов, сервисы — от репозиториев (`CategoryRepository`, ItemRepository, `MovementRepository`).

Комментарий:  
Диаграмма классов показывает структуру доменной модели и связи между сущностями, а также разделение на слои (контроллеры, сервисы, репозитории).

---

### 2.6.2. UML диаграмма прецедентов (Use Case) — поведенческая

Актор: Оператор склада.

Прецеденты:

-«Управлять категориями» (создать/редактировать/удалить категорию).
- «Управлять товарами» (создать/редактировать/удалить товар, просмотреть список).
- «Просмотреть остатки по товарам».
- «Зарегистрировать приход товара».
- «Зарегистрировать расход товара».
- «Работать с QR-кодами» (получить данные по QR-коду товара).

Взаимосвязи:

- Прецеденты «Зарегистрировать приход товара» и «Зарегистрировать расход товара» include прецедент «Просмотреть/выбрать товар».
- Прецедент «Работать с QR-кодами» include прецедент «Просмотреть товар».

Комментарий:  
Use Case диаграмма отображает функциональные возможности системы с точки зрения пользователя и помогает убедиться, что реализованы все ключевые сценарии работы склада.

---

### 2.6.3. UML диаграмма последовательности (Sequence) — поведенческая

Сценарий: «Регистрация расхода товара».

Участники:  
Operator → ItemController → ItemService → ItemRepository → MovementService → MovementRepository.

Основной поток:

1. Operator отправляет HTTP-запрос POST /api/movements/remove-stock/{itemId} с RemoveStockDto.
2. MovementController принимает запрос и вызывает movementService.removeStock(itemId, dto).
3. MovementService запрашивает товар у ItemRepository.findById(itemId).
4. Проверяется текущий count товара:
    - Если count < dto.amount — выбрасывается NotEnoughStockException, которая перехватывается GlobalExceptionHandler, и пользователю возвращается ошибка.
    - Иначе:
5. MovementService создаёт объект Movement с типом OUT, уменьшает item.count, сохраняет Movement через MovementRepository.save(movement) и обновляет товар через ItemRepository.save(item).
6. MovementService возвращает сущность Movement, далее MovementMapper преобразует её в MovementRequestDto.
7. MovementController возвращает DTO в виде JSON-ответа оператору.

Комментарий:  
Диаграмма последовательности демонстрирует взаимодействие между слоями при выполнении операции расхода товара и показывает, на каком этапе происходит проверка остатков и обработка ошибок.