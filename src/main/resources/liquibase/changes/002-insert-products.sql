--liquibase formatted sql

--changeset farida:2
insert into products (name, price, quantity, type, is_expired, expiry_date, manufacture_date)
values
    ('Laptop', 1500.00, 10, 'ELECTRONICS', false, '2028-01-01', '2024-01-01'),
    ('Phone', 800.00, 25, 'ELECTRONICS', false, '2029-01-01', '2024-06-01'),
    ('Milk', 2.50, 50, 'GROCERY', true, '2026-10-20', '2026-10-10');


-- rollback delete from products where name in ('Laptop', 'Phone', 'Milk');