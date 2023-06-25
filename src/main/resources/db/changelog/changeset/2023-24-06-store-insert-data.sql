--liquibase formatted sql
--changeset EuBud:store-insert-data

INSERT INTO stores (name, address, phone, working_hours)
VALUES ('Store A', 'Address 1', '123-456-7890', '9 AM - 6 PM');

INSERT INTO stores (name, address, phone, working_hours)
VALUES ('Store B', 'Address 2', '987-654-3210', '10 AM - 8 PM');

INSERT INTO products (name, price, quantity, description, store_id)
VALUES ('Product X', 9.99, 100, 'Description X', 1);

INSERT INTO products (name, price, quantity, description, store_id)
VALUES ('Product Y', 19.99, 50, 'Description Y', 2);

INSERT INTO products (name, price, quantity, description, store_id)
VALUES ('Product Z', 14.99, 75, 'Description Z', 2);