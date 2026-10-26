-- liquibase formatted sql
-- changeset farida:1
create table products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price NUMERIC(38, 2) NOT NULL,
                          customer_id BIGINT,
                          quantity INTEGER,
                          type VARCHAR(255),
                          is_expired BOOLEAN,
                          expiry_date DATE,
                          manufacture_date DATE
);
-- rollback drop table products;