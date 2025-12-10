# SmartStorage — Ментальная карта

## Диаграмма ментальной карты

```plantuml
@startuml
class User {
  - username: String
  - password: String
  - role: String
  --
  + createItem(Item): void
  + updateItem(Item): void
  + registerMovement(Movement): void
  + scanQrCode(QrCode): Item
}

class Category {
  - name: String
  - description: String
  --
  + addItem(Item): void
  + removeItem(Item): void
}

class Item {
  - name: String
  - description: String
  - count: int
  - category: Category
  --
  + getQrCode(): QrCode
  + updateCount(int): void
}

class Movement {
  - item: Item
  - type: enum {IN, OUT}
  - quantity: int
  - dateTime: Date
  - comment: String
  --
  + isIncoming(): boolean
  + isOutgoing(): boolean
}

class QrCode {
  - code: String
  - item: Item
  --
  + generate(): void
  + scan(): Item
}

class RESTAPI {
  --
  + CategoryController
  + ItemController
  + MovementController
  + QrCodeController
}

class Database {
  --
  + category
  + item
  + movement
}

User --> Item
User --> Movement
User --> QrCode
Category --> Item
Item --> Movement
Item --> QrCode
Database --> Category
Database --> Item
Database --> Movement
RESTAPI --> Category
RESTAPI --> Item
RESTAPI --> Movement
RESTAPI --> QrCode
@enduml


## Подпись

Диаграмма отражает основные сущности SmartStorage, их связи и поток действий: оператор использует REST API и QR-коды для управления категориями, товарами и движениями, а данные сохраняются в PostgreSQL.
