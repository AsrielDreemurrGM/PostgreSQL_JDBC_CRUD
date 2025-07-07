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
		return client;
	}

	@Override
	protected void setUpdateParameters(PreparedStatement statement, Client entity) throws Exception {
		statement.setString(1, entity.getEntityName());
		statement.setString(2, entity.getEntityCode());
	}

	@Override
	protected String getRegisterSql() {
		return "INSERT INTO " + getTableName() + " (id, code, name) VALUES (nextval('sq_client'), ?, ?)";
	}

	@Override
	protected void setRegisterParameters(PreparedStatement statement, Client entity) throws Exception {
		statement.setString(1, entity.getEntityCode());
		statement.setString(2, entity.getEntityName());
	}
}
