<h1>Projeto PostgreSQL JDBC CRUD</h1>
<p>
  This README is also available in <a href="./README.md">English</a>.
</p>
<p>
  Este projeto é uma aplicação backend em Java que demonstra como implementar um sistema completo de CRUD utilizando
  <strong>PostgreSQL</strong>, <strong>JDBC</strong> e <strong>JUnit 5</strong> para testes.
  Ele aplica práticas de código limpo e padrões de projeto como <strong>Generics</strong>, <strong>Anotações</strong> e <strong>Reflexão</strong> para criação de DAOs reutilizáveis e automatizados.
</p>
<h2>🚀 Principais Funcionalidades</h2>
<ul>
  <li>✅ Conexão com banco de dados PostgreSQL usando JDBC;</li>
  <li>✅ DAO genérico utilizando Java Reflection e anotações personalizadas (<code>@Table</code>, <code>@Column</code>);</li>
  <li>✅ Modelos de entidade como <code>Client</code>, <code>Product</code> e <code>Inventory</code>, com campos personalizados e relacionamento entre tabelas;</li>
  <li>✅ Operações completas de CRUD no PostgreSQL para todas as entidades;</li>
  <li>✅ Desenvolvimento orientado a testes (TDD) com <strong>JUnit 5</strong>;</li>
  <li>✅ Documentação com Javadoc em todas as classes principais, interfaces e testes;</li>
  <li>✅ Uso de variáveis de ambiente para configuração segura da conexão com o banco de dados;</li>
  <li>✅ Inclusão do script <code>schema.sql</code> para criação rápida de toda a estrutura do banco de dados.</li>
</ul>
<h2>🔐 Configuração de Ambiente</h2>
<p>
  Para conectar-se ao banco de dados PostgreSQL, é necessário configurar as seguintes variáveis de ambiente:
</p>
<ul>
  <li><code>DB_URL</code> — A URL JDBC do seu banco PostgreSQL (exemplo: <code>jdbc:postgresql://localhost:5432/online_selling</code>);</li>
  <li><code>DB_USERNAME</code> — Seu usuário do PostgreSQL;</li>
  <li><code>DB_PASSWORD</code> — Sua senha do PostgreSQL.</li>
</ul>
<p>
  Essas variáveis são carregadas automaticamente durante a execução da aplicação para estabelecer a conexão com o banco de dados.
</p>
<h2>🗂️ Estrutura do Projeto</h2>
<ul>
  <li><code>br.com.eaugusto.domain</code>: Classes de entidades como <code>Client</code>, <code>Product</code> e <code>Inventory</code>, e a interface <code>IPersistable</code>;</li>
  <li><code>br.com.eaugusto.dao</code>: Interfaces DAO e suas implementações, com suporte a reflexão e mapeamento automático;</li>
  <li><code>br.com.eaugusto.dao.generics</code>: Implementações base genéricas de DAO com operações de CRUD reutilizáveis e anotadas;</li>
  <li><code>br.com.eaugusto</code> (testes): Testes unitários e de integração com JUnit 5 para todas as entidades e DAO;</li>
  <li><code>schema.sql</code>: Script SQL para criar todas as tabelas e sequências necessárias.</li>
</ul>
<h2>🧪 Abordagem de Testes</h2>
<ul>
  <li>✅ Testes escritos com <strong>JUnit 5</strong> e organizados por entidade;</li>
  <li>✅ Metodologia TDD aplicada em todas as fases do projeto;</li>
  <li>✅ Testes de conexão, inserção, busca, atualização e remoção em banco real;</li>
  <li>✅ Testes para exceções personalizadas, como falhas de conexão, mapeamento e parâmetros inválidos;</li>
  <li>✅ Uso de <code>@BeforeEach</code> e <code>@AfterEach</code> para isolar dados e restaurar o estado inicial após cada teste.</li>
</ul>
<h2>📋 Tecnologias Utilizadas</h2>
<ul>
  <li>Java 17 ou superior;</li>
  <li>PostgreSQL;</li>
  <li>JDBC;</li>
  <li>JUnit 5;</li>
  <li>Variáveis de ambiente para configuração segura;</li>
  <li>Reflexão e anotações personalizadas para automação de DAOs.</li>
</ul>
<h2>📑 Objetivos de Aprendizado</h2>
<ul>
  <li>Aplicar TDD em projetos backend com foco em testes reais de banco;</li>
  <li>Utilizar JDBC e SQL nativo com qualidade e segurança;</li>
  <li>Automatizar o mapeamento de entidades com uso de reflexão e anotações;</li>
  <li>Aplicar boas práticas de organização de pacotes, testes e documentação.</li>
</ul>
<h2>📂 Estrutura do Banco de Dados (schema.sql)</h2>
<a href="./schema.sql" target="_blank">📄 Link para o schema.sql (Script SQL)</a>
<p>
  O script <code>schema.sql</code> cria toda a estrutura necessária do banco de dados:
</p>
<ul>
  <li>Banco de dados: <code>online_selling</code></li>
  <li>Tabelas: <code>tb_client</code>, <code>tb_product</code> e <code>tb_inventory</code></li>
  <li>Sequências: <code>sq_client</code>, <code>sq_product</code> e <code>sq_inventory</code> para geração automática de IDs.</li>
</ul>
<h3>Destaques do Script:</h3>
<pre><code>-- Cria o banco de dados
CREATE DATABASE online_selling;

-- Lembre-se de mudar para o banco criado antes de executar o restante do script

-- Tabela de clientes
CREATE TABLE IF NOT EXISTS tb_client (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    code VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20)
);

-- Sequência para IDs de clientes
CREATE SEQUENCE IF NOT EXISTS sq_client
    START WITH 1
    INCREMENT BY 1
    OWNED BY tb_client.id;

-- Tabela de produtos
CREATE TABLE IF NOT EXISTS tb_product (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    code VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    price NUMERIC(10, 2),
    stock_quantity INTEGER
);

-- Sequência para IDs de produtos
CREATE SEQUENCE IF NOT EXISTS sq_product
    START WITH 1
    INCREMENT BY 1
    OWNED BY tb_product.id;

-- Tabela de inventário (registro de vendas)
CREATE TABLE IF NOT EXISTS tb_inventory (
    id BIGINT PRIMARY KEY,
    client_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity_sold INTEGER NOT NULL,
    sale_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES tb_client (id),
    FOREIGN KEY (product_id) REFERENCES tb_product (id)
);

-- Sequência para IDs de inventário
CREATE SEQUENCE IF NOT EXISTS sq_inventory
    START WITH 1
    INCREMENT BY 1
    OWNED BY tb_inventory.id;
</code></pre>
