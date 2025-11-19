-- ================================
-- CATEGORY TABLE
-- ================================
CREATE TABLE category (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL UNIQUE,
                          description TEXT
);

-- ================================
-- ITEM TABLE
-- ================================
CREATE TABLE item (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      description TEXT,
                      count INTEGER NOT NULL DEFAULT 0,
                      category_id BIGINT,
                      qr_code TEXT,

                      CONSTRAINT fk_item_category
                          FOREIGN KEY (category_id)
                              REFERENCES category(id)
                              ON DELETE SET NULL
);

-- ================================
-- MOVEMENT TABLE
-- ================================
CREATE TABLE movement (
                          id BIGSERIAL PRIMARY KEY,
                          item_id BIGINT NOT NULL,
                          type VARCHAR(20) NOT NULL,
                          amount INTEGER NOT NULL,
                          created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                          comment TEXT,

                          CONSTRAINT fk_movement_item
                              FOREIGN KEY (item_id)
                                  REFERENCES item(id)
                                  ON DELETE CASCADE
);