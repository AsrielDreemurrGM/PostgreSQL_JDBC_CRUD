-- Creates the database for the project
CREATE DATABASE online_selling;

-- Remember to switch to the new database before running the below script;

-- Creates the client table
CREATE TABLE IF NOT EXISTS tb_client (
	id BIGINT PRIMARY KEY,
	code VARCHAR(50) NOT NULL,
	name VARCHAR(50) NOT NULL,
	cpf VARCHAR(20),
	phone VARCHAR(20),
	address VARCHAR(50),
	address_number VARCHAR(10),
	city VARCHAR(50),
	state VARCHAR(50),
	birth_date VARCHAR(20)
);

-- Creates the sequence for client IDs
CREATE SEQUENCE IF NOT EXISTS sq_client
    START WITH 1
    INCREMENT BY 1
    OWNED BY tb_client.id;

-- Creates the product table
CREATE TABLE IF NOT EXISTS tb_product (
	id BIGINT PRIMARY KEY,
	code VARCHAR(10) NOT NULL,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(100),
	price NUMERIC(10, 2),
	stock_quantity INTEGER,
	category VARCHAR(30)
);

-- Creates the sequence for product IDs
CREATE SEQUENCE IF NOT EXISTS sq_product
    START WITH 1
    INCREMENT BY 1
    OWNED BY tb_product.id;

-- Creates the inventory table
CREATE TABLE IF NOT EXISTS tb_inventory (
	id BIGINT PRIMARY KEY,
	client_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	quantity_sold INTEGER NOT NULL,
	sale_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (client_id) REFERENCES tb_client (id),
	FOREIGN KEY (product_id) REFERENCES tb_product (id)
);

-- Creates the sequence for inventory IDs
CREATE SEQUENCE IF NOT EXISTS sq_inventory
    START WITH 1
    INCREMENT BY 1
    OWNED BY tb_inventory.id;
