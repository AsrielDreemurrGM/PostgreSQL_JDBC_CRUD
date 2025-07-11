package br.com.eaugusto.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.eaugusto.dao.generics.GenericDAO;
import br.com.eaugusto.domain.Client;
import br.com.eaugusto.exceptions.DAOParameterException;

/**
 * DAO implementation for {@link Client} entities using {@link GenericDAO}.
 *
 * <p>
 * SQL statements are automatically generated based on entity annotations. This
 * class provides parameter mappings for client-specific operations.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025 (updated July 10, 2025 for SQL refactoring)
 */
public class ClientDAO extends GenericDAO<Client> implements IClientDAO {

	@Override
	protected void setUpdateParameters(PreparedStatement statement, Client entity) throws DAOParameterException {
		try {
			statement.setString(1, entity.getEntityName());
			statement.setString(2, entity.getEmail());
			statement.setString(3, entity.getPhone());
			statement.setString(4, entity.getEntityCode());
		} catch (SQLException e) {
			throw new DAOParameterException("Error setting register parameters for Client.", e);
		}
	}

	@Override
	protected void setRegisterParameters(PreparedStatement statement, Client entity) throws DAOParameterException {
		try {
			statement.setString(1, entity.getEntityCode());
			statement.setString(2, entity.getEntityName());
			statement.setString(3, entity.getEmail());
			statement.setString(4, entity.getPhone());
		} catch (SQLException e) {
			throw new DAOParameterException("Error setting register parameters for Client.", e);
		}
	}

	@Override
	protected Class<Client> getEntityClass() {
		return Client.class;
	}
}
