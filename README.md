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
  <li>✅ Entity models like <code>Client</code> and <code>Product</code> with custom fields and behaviors;</li>
  <li>✅ Full CRUD operations with PostgreSQL for both entities;</li>
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
  <li><code>br.com.eaugusto.domain</code>: Entity classes like <code>Client</code> and <code>Product</code>, along with the <code>IPersistable</code> interface;</li>
  <li><code>br.com.eaugusto.dao</code>: DAO interfaces and classes, including the generic DAO layer;</li>
  <li><code>br.com.eaugusto.dao.generics</code>: Generic DAO base implementations for reusable CRUD operations;</li>
  <li><code>br.com.eaugusto</code> (tests): Contains all test classes, including DAO and database connection tests;</li>
  <li><code>schema.sql</code>: SQL file for creating the necessary tables and sequences.</li>
</ul>
<h2>🧪 Testing Approach</h2>
<ul>
  <li>✅ Tests written using <strong>JUnit 5</strong>;</li>
  <li>✅ TDD methodology followed throughout development;</li>
  <li>✅ Database connection tests to ensure connectivity and stability;</li>
  <li>✅ Comprehensive CRUD tests for both <code>Client</code> and <code>Product</code> DAOs;</li>
  <li>✅ Mock data creation and validation via assertions in every test case.</li>
</ul>
<h2>📋 Technologies Used</h2>
<ul>
  <li>Java 17+;</li>
  <li>PostgreSQL;</li>
  <li>JDBC;</li>
  <li>JUnit 5;</li>
  <li>Environment Variables for configuration.</li>
</ul>
<h2>📑 Learning Goals</h2>
<ul>
  <li>Apply Test-Driven Development (TDD) to backend projects;</li>
  <li>Master the use of JDBC for database operations in Java;</li>
  <li>Design flexible DAOs using <strong>Generics</strong>;</li>
  <li>Ensure clean, maintainable code with full documentation and tests.</li>
</ul>
<h2>📂 Database Schema (schema.sql)</h2>
<a href="./schema.sql" target="_blank">📄 Link to schema.sql (SQL Script)</a>
<p>
  The provided <code>schema.sql</code> script creates the required database structure for the application:
</p>
<ul>
  <li>Database: <code>online_selling</code></li>
  <li>Tables: <code>tb_client</code> and <code>tb_product</code></li>
  <li>Sequences: <code>sq_client</code> and <code>sq_product</code> for auto-incrementing IDs</li>
</ul>
<h3>Script Highlights:</h3>
<pre>
  <code>
-- Creates the database
  CREATE DATABASE online_selling;
  
  -- Switch to the new database before running the rest
  
  -- Client table
  CREATE TABLE IF NOT EXISTS tb_client (
      id BIGINT PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      code VARCHAR(50) NOT NULL
  );
  
  -- Sequence for client IDs
  CREATE SEQUENCE IF NOT EXISTS sq_client
      START WITH 1
      INCREMENT BY 1
      OWNED BY tb_client.id;
  
  -- Product table
  CREATE TABLE IF NOT EXISTS tb_product (
      id BIGINT PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      code VARCHAR(50) NOT NULL,
      description VARCHAR(50) NOT NULL
  );
  
  -- Sequence for product IDs
  CREATE SEQUENCE IF NOT EXISTS sq_product
      START WITH 1
      INCREMENT BY 1
      OWNED BY tb_product.id;
  </code>
</pre>
