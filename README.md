<h1>PostgreSQL JDBC CRUD Project</h1>
<p>
  Esse README também esta disponível em <a href="./README_PT-BR.md">Português Brasileiro</a>.
</p>
<p>
  This project is a backend Java application that demonstrates how to implement a full CRUD system using
  <strong>PostgreSQL</strong>, <strong>JDBC</strong>, and <strong>JUnit 5</strong> for testing.
  It applies clean code practices and design patterns such as <strong>Generics</strong> for reusable DAO layers.
</p>
<h2>🚀 Key Features</h2>
<ul>
  <li>✅ Database connection through PostgreSQL using JDBC;</li>
  <li>✅ Generic DAO implementation to reduce repetitive code;</li>
  <li>✅ Entity models like <code>Client</code>, <code>Product</code>, and <code>Inventory</code> with custom fields and behaviors;</li>
  <li>✅ Full CRUD operations with PostgreSQL for all entities;</li>
  <li>✅ Test-driven development (TDD) approach using <strong>JUnit 5</strong>;</li>
  <li>✅ Javadoc documentation for all main classes and interfaces;</li>
  <li>✅ Usage of environment variables for secure database connection configuration;</li>
  <li>✅ Schema creation script (<code>schema.sql</code>) included for easy database setup.</li>
</ul>
<h2>🔐 Environment Configuration</h2>
<p>
  To connect to the PostgreSQL database, you must configure the following environment variables:
</p>
<ul>
  <li><code>DB_URL</code> — The JDBC URL of your PostgreSQL database (e.g., <code>jdbc:postgresql://localhost:5432/online_selling</code>);</li>
  <li><code>DB_USERNAME</code> — Your PostgreSQL username;</li>
  <li><code>DB_PASSWORD</code> — Your PostgreSQL password.</li>
</ul>
<p>
  These variables are securely loaded at runtime by the application to establish the database connection.
</p>
<h2>🗂️ Project Structure</h2>
<ul>
  <li><code>br.com.eaugusto.domain</code>: Entity classes like <code>Client</code>, <code>Product</code>, and <code>Inventory</code>, plus the <code>IPersistable</code> interface;</li>
  <li><code>br.com.eaugusto.dao</code>: DAO interfaces and implementations for all entities;</li>
  <li><code>br.com.eaugusto.dao.generics</code>: Generic DAO base implementations using annotations and reflection;</li>
  <li><code>br.com.eaugusto</code> (tests): JUnit test classes for DAO and connection testing;</li>
  <li><code>schema.sql</code>: SQL script to create all tables and sequences for the database.</li>
</ul>
<h2>🧪 Testing Approach</h2>
<ul>
  <li>✅ Tests written using <strong>JUnit 5</strong>;</li>
  <li>✅ TDD methodology used during development;</li>
  <li>✅ Full integration tests for CRUD and database operations;</li>
  <li>✅ DAOException, MappingException, and connection error testing included;</li>
  <li>✅ Extensive assertion usage and rollback logic after each test.</li>
</ul>
<h2>📋 Technologies Used</h2>
<ul>
  <li>Java 17+;</li>
  <li>PostgreSQL;</li>
  <li>JDBC;</li>
  <li>JUnit 5;</li>
  <li>Environment Variables;</li>
  <li>Reflection and Annotations in Java.</li>
</ul>
<h2>📑 Learning Goals</h2>
<ul>
  <li>Build reusable DAOs using generics and annotations;</li>
  <li>Apply TDD in backend development;</li>
  <li>Securely configure environment variables for DB access;</li>
  <li>Structure a professional-grade backend Java application;</li>
  <li>Use Java Reflection for dynamic SQL generation.</li>
</ul>
<h2>📂 Database Schema (schema.sql)</h2>
<a href="./schema.sql" target="_blank">📄 Link to schema.sql (SQL Script)</a>
<p>
  The provided <code>schema.sql</code> script creates the full structure needed for the application:
</p>
<ul>
  <li>Database: <code>online_selling</code></li>
  <li>Tables: <code>tb_client</code>, <code>tb_product</code>, and <code>tb_inventory</code></li>
  <li>Sequences: <code>sq_client</code>, <code>sq_product</code>, <code>sq_inventory</code></li>
</ul>
<h3>Script Highlights:</h3>
<pre>
  <code>
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
  </code>
</pre>
