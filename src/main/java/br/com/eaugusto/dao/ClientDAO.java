package br.com.eaugusto.dao;

import java.sql.PreparedStatement;

import br.com.eaugusto.dao.generics.GenericDAO;
import br.com.eaugusto.domain.Client;

/**
 * DAO implementation for {@link Client} entities using {@link GenericDAO}.
 * 
 * <p>
 * Provides SQL statements and parameter mappings for client-specific
 * operations. Entity mapping is handled automatically by the generic superclass
 * using annotations.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public class ClientDAO extends GenericDAO<Client> implements IClientDAO {

	@Override
	protected void setUpdateParameters(PreparedStatement statement, Client entity) throws Exception {
		statement.setString(1, entity.getEntityName());
		statement.setString(2, entity.getEmail());
		statement.setString(3, entity.getPhone());
		statement.setString(4, entity.getEntityCode());
	}

	@Override
	protected void setRegisterParameters(PreparedStatement statement, Client entity) throws Exception {
		statement.setString(1, entity.getEntityCode());
		statement.setString(2, entity.getEntityName());
		statement.setString(3, entity.getEmail());
		statement.setString(4, entity.getPhone());
	}

	@Override
	protected Class<Client> getEntityClass() {
		return Client.class;
	}
}
