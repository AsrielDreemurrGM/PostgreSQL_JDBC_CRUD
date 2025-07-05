-- Creates the database for the project
CREATE DATABASE online_selling;

-- Remember to switch to the new database now;

-- Creates the client table
CREATE TABLE IF NOT EXISTS tb_client (
	id BIGINT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	code VARCHAR(50) NOT NULL
);

-- Creates the sequence for client IDs
CREATE SEQUENCE IF NOT EXISTS sq_client
    START WITH 1
    INCREMENT BY 1
    OWNED BY tb_client.id;
