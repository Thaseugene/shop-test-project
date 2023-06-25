--liquibase formatted sql
--changeset EuBud:store-create-table

CREATE TABLE stores (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        address VARCHAR(100) NOT NULL,
                        phone VARCHAR(20) NOT NULL,
                        working_hours VARCHAR(50) NOT NULL
);