-- liquibase formatted sql

--changeset farida:4
delete from products
where is_expired = true;

-- rollback update products set is_expired = true where is_expired = false;