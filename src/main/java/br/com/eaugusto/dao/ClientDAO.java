package br.com.eaugusto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.eaugusto.dao.generics.GenericDAO;
import br.com.eaugusto.domain.Client;

/**
 * DAO implementation for Client using GenericDAO and PostgreSQL.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public class ClientDAO extends GenericDAO<Client> implements IClientDAO {

	@Override
	protected String getTableName() {
		return "tb_client";
	}

	@Override
	protected Client mapResult(ResultSet result) throws Exception {
		Client client = new Client();
		client.setId(result.getLong("id"));
		client.setCode(result.getString("code"));
		client.setName(result.getString("name"));
		client.setEmail(result.getString("email")); // new
		client.setPhone(result.getString("phone")); // new
		return client;
	}

	@Override
	protected void setUpdateParameters(PreparedStatement statement, Client entity) throws Exception {
		statement.setString(1, entity.getEntityName());
		statement.setString(2, entity.getEmail()); // new
		statement.setString(3, entity.getPhone()); // new
		statement.setString(4, entity.getEntityCode());
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE " + getTableName() + " SET name = ?, email = ?, phone = ? WHERE code = ?";
	}

	@Override
	protected String getSelectSql() {
		return "SELECT id, code, name, email, phone FROM " + getTableName();
	}

	@Override
	protected String getRegisterSql() {
		return "INSERT INTO " + getTableName()
				+ " (id, code, name, email, phone) VALUES (nextval('sq_client'), ?, ?, ?, ?)";
	}

	@Override
	protected void setRegisterParameters(PreparedStatement statement, Client entity) throws Exception {
		statement.setString(1, entity.getEntityCode());
		statement.setString(2, entity.getEntityName());
		statement.setString(3, entity.getEmail());
		statement.setString(4, entity.getPhone());
	}
}
