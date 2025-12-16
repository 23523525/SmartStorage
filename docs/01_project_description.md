## 2.1. Подробное описание проекта

Проект реализован на платформе Spring Boot, логика разделена на слои:

- Сущности (Entity):
    - Category — категория товара (id, name, description, список связанных товаров).
    - Item — товар (id, name, description, count, ссылка на категорию).
    - Movement — движение товара (id, ссылка на товар, тип операции IN/OUT, amount, created_at, comment).

- DTO (в пакете `dto`):  
  Для каждой сущности и операции определены отдельные классы для запросов и ответов (например, CreateCategoryDto, UpdateCategoryDto, CategoryResponseDto, CreateItemDto, ItemResponseDto, MovementRequestDto, AddStockDto, RemoveStockDto, QrCodeRequestDto, `QrCodeResponseDto`).

- Mapper (пакет `mapper`):  
  Классы CategoryMapper, ItemMapper, MovementMapper преобразуют сущности в DTO и обратно, изолируя внутреннюю модель БД от внешнего API.

- Service (пакет `service`):
    - CategoryService — управление категориями (создание, обновление, удаление, получение списка).
    - ItemService — управление товарами и их остатками, получение QR-кодов.
    - MovementService — регистрация движений (приход/расход) по товарам, изменение поля count у соответствующего Item.
    - QrCodeService — обработка полезной нагрузки QR-кода (например, получение информации о товаре по коду).

- Controller (пакет `controller`):
    - CategoryController (маршрут `/api/categories`) — CRUD-операции с категориями и получение количества товаров в категории.
    - ItemController (маршрут `/api/items`) — CRUD-операции с товарами, выборка по категории (`/by-category/{categoryId}`), получение QR-кода товара (`/qr/{id}`).
    - MovementController (маршрут `/api/movements`) — просмотр списка движений, добавление операций прихода (`/add-stock/{itemId}`) и расхода (`/remove-stock/{itemId}`).
    - QrCodeController (маршрут `/api/qr`) — приём QR-кода и возврат результата обработки.

База данных PostgreSQL настраивается в application.yaml с указанием параметров источника данных, включённой миграцией Flyway (`main/resources/db/migration`). Модуль SwaggerConfig формирует описание OpenAPI/Swagger.

Система обрабатывает ошибки с помощью центрального обработчика (`GlobalExceptionHandler` и классов исключений NotFoundException, NotEnoughStockException, InvalidQrException, `ApiError`), что обеспечивает единый формат ответов при ошибках.