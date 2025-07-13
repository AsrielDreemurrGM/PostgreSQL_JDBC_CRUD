package br.com.eaugusto.dao;

import java.sql.Date;
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
	protected void setRegisterParameters(PreparedStatement statement, Client entity) throws DAOParameterException {
		try {
			statement.setString(1, entity.getEntityCode());
			statement.setString(2, entity.getEntityName());
			statement.setString(3, entity.getCpf());
			statement.setString(4, entity.getPhone());
			statement.setString(5, entity.getAddress());
			statement.setString(6, entity.getAddressNumber());
			statement.setString(7, entity.getCity());
			statement.setString(8, entity.getState());
			statement.setDate(9, Date.valueOf(entity.getBirthDate()));
		} catch (SQLException e) {
			throw new DAOParameterException("Error setting register parameters for Client.", e);
		}
	}

	@Override
	protected void setUpdateParameters(PreparedStatement statement, Client entity) throws DAOParameterException {
		try {
			statement.setString(1, entity.getEntityName());
			statement.setString(2, entity.getCpf());
			statement.setString(3, entity.getPhone());
			statement.setString(4, entity.getAddress());
			statement.setString(5, entity.getAddressNumber());
			statement.setString(6, entity.getCity());
			statement.setString(7, entity.getState());
			statement.setDate(8, Date.valueOf(entity.getBirthDate()));
			statement.setString(9, entity.getEntityCode());
		} catch (SQLException e) {
			throw new DAOParameterException("Error setting update parameters for Client.", e);
		}
	}

	@Override
	protected Class<Client> getEntityClass() {
		return Client.class;
	}
}
