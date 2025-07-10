<h1>Projeto PostgreSQL JDBC CRUD</h1>
<p>
  This README is also available in <a href="./README.md">English</a>.
</p>
<p>
  Este projeto é uma aplicação backend em Java que demonstra como implementar um sistema completo de CRUD utilizando
  <strong>PostgreSQL</strong>, <strong>JDBC</strong> e <strong>JUnit 5</strong> para testes.
  Ele aplica práticas de código limpo e padrões de projeto como <strong>Generics</strong> para DAOs reutilizáveis.
</p>
<h2>🚀 Principais Funcionalidades</h2>
<ul>
  <li>✅ Conexão com banco de dados PostgreSQL usando JDBC;</li>
  <li>✅ Implementação de DAO genérico para reduzir repetição de código;</li>
  <li>✅ Modelos de entidades como <code>Client</code> e <code>Product</code> com campos e comportamentos personalizados;</li>
  <li>✅ Operações completas de CRUD no PostgreSQL para ambas as entidades;</li>
  <li>✅ Desenvolvimento orientado a testes (TDD) com <strong>JUnit 5</strong>;</li>
  <li>✅ Documentação com Javadoc para todas as classes e interfaces principais;</li>
  <li>✅ Uso de variáveis de ambiente para configurar a conexão ao banco de dados de forma segura;</li>
  <li>✅ Inclusão do script <code>schema.sql</code> para criação rápida do banco de dados.</li>
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
  Essas variáveis são carregadas automaticamente durante a execução da aplicação para estabelecer a conexão com o banco.
</p>
<h2>🗂️ Estrutura do Projeto</h2>
<ul>
  <li><code>br.com.eaugusto.domain</code>: Classes de entidades como <code>Client</code> e <code>Product</code>, além da interface <code>IPersistable</code>;</li>
  <li><code>br.com.eaugusto.dao</code>: Interfaces e classes DAO, incluindo a camada genérica de DAO;</li>
  <li><code>br.com.eaugusto.dao.generics</code>: Implementações base genéricas para operações de CRUD reutilizáveis;</li>
  <li><code>br.com.eaugusto</code> (testes): Contém todas as classes de testes, incluindo testes de DAOs e de conexão ao banco de dados;</li>
  <li><code>schema.sql</code>: Script SQL para criar as tabelas e sequências necessárias no banco de dados.</li>
</ul>
<h2>🧪 Abordagem de Testes</h2>
<ul>
  <li>✅ Testes escritos com <strong>JUnit 5</strong>;</li>
  <li>✅ Metodologia TDD aplicada ao longo de todo o desenvolvimento;</li>
  <li>✅ Testes de conexão com o banco de dados para garantir estabilidade e conectividade;</li>
  <li>✅ Testes completos de CRUD para os DAOs de <code>Client</code> e <code>Product</code>;</li>
  <li>✅ Criação de dados simulados e validação com asserções em todos os casos de teste.</li>
</ul>
<h2>📋 Tecnologias Utilizadas</h2>
<ul>
  <li>Java 17+;</li>
  <li>PostgreSQL;</li>
  <li>JDBC;</li>
  <li>JUnit 5;</li>
  <li>Variáveis de ambiente para configuração segura.</li>
</ul>
<h2>📑 Objetivos de Aprendizado</h2>
<ul>
  <li>Aplicar Desenvolvimento Orientado a Testes (TDD) em projetos backend;</li>
  <li>Dominar o uso do JDBC para operações com banco de dados em Java;</li>
  <li>Projetar DAOs flexíveis usando <strong>Generics</strong>;</li>
  <li>Garantir código limpo, bem documentado e com testes completos.</li>
</ul>
<h2>📂 Estrutura do Banco de Dados (schema.sql)</h2>
<a href="./schema.sql" target="_blank">📄 Link para o schema.sql (SQL Script)</a>
<p>
  O script <code>schema.sql</code> incluído no projeto cria toda a estrutura necessária para o banco de dados:
</p>
<ul>
  <li>Banco de dados: <code>online_selling</code></li>
  <li>Tabelas: <code>tb_client</code> e <code>tb_product</code></li>
  <li>Sequências: <code>sq_client</code> e <code>sq_product</code> para IDs automáticos.</li>
</ul>
<h3>Destaques do Script:</h3>
<pre><code>-- Cria o banco de dados
CREATE DATABASE online_selling;

-- Troque para o banco de dados criado antes de rodar o restante

-- Tabela de clientes
CREATE TABLE IF NOT EXISTS tb_client (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    code VARCHAR(50) NOT NULL
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
    description VARCHAR(50) NOT NULL
);

-- Sequência para IDs de produtos
CREATE SEQUENCE IF NOT EXISTS sq_product
    START WITH 1
    INCREMENT BY 1
    OWNED BY tb_product.id;
</code></pre>
