-- Creates the database for the project
CREATE DATABASE online_selling;

-- Remember to switch to the new database before running the below script;

-- Creates the client table
CREATE TABLE IF NOT EXISTS tb_client (
	id BIGINT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	code VARCHAR(50) NOT NULL,
	email VARCHAR(100),
	phone VARCHAR(20)
);

-- Creates the sequence for client IDs
CREATE SEQUENCE IF NOT EXISTS sq_client
    START WITH 1
    INCREMENT BY 1
    OWNED BY tb_client.id;

-- Creates the product table
CREATE TABLE IF NOT EXISTS tb_product (
	id BIGINT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	code VARCHAR(50) NOT NULL,
	description VARCHAR(100),
	price NUMERIC(10, 2),
	stock_quantity INTEGER
);

-- Creates the sequence for product IDs
CREATE SEQUENCE IF NOT EXISTS sq_product
    START WITH 1
    INCREMENT BY 1
    OWNED BY tb_product.id;
