--liquibase formatted sql
--changeset EuBud:product-create-table

CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          price DECIMAL(10, 2) NOT NULL,
                          quantity INTEGER NOT NULL,
                          description VARCHAR(255) NOT NULL,
                          store_id INTEGER REFERENCES stores (id) ON DELETE CASCADE
);