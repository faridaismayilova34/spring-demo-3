-- liquibase formatted sql

-- changeset farida:3
update products
set price = 1400.00, quantity = 8
where name = 'Laptop';

-- rollback update products set price = 1500.00, quantity = 10 where name = 'Laptop';