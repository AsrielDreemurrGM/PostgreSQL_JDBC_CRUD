package br.com.eaugusto.dao.generics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.eaugusto.domain.IPersistable;
import br.com.eaugusto.generic.jdbc.ConnectionFactory;

/**
 * Generic DAO base class for common database operations.
 *
 * @param <T> Entity type
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 6, 2025
 */
public abstract class GenericDAO<T extends IPersistable> implements IGenericDAO<T> {

	protected abstract String getTableName();

	protected abstract T mapResult(ResultSet result) throws Exception;

	protected abstract void setUpdateParameters(PreparedStatement statement, T entity) throws Exception;

	@Override
	public Integer register(T entity) throws Exception {
		String sql = getRegisterSql();

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			setRegisterParameters(statement, entity);

			return statement.executeUpdate();
		}
	}

	@Override
	public T search(String code) throws Exception {
		String sql = getSelectSql() + " WHERE code = ?";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, code);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					return mapResult(result);
				}
			}
		}
		return null;
	}

	@Override
	public Integer delete(T entity) throws Exception {
		String sql = "DELETE FROM " + getTableName() + " WHERE code = ?";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, entity.getEntityCode());

			return statement.executeUpdate();
		}
	}

	@Override
	public List<T> searchAll() throws Exception {
		String sql = getSelectSql();

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet result = statement.executeQuery()) {

			List<T> entities = new ArrayList<>();
			while (result.next()) {
				entities.add(mapResult(result));
			}
			return entities;
		}
	}

	@Override
	public Integer update(T entity) throws Exception {
		String sql = getUpdateSql();

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			setUpdateParameters(statement, entity);
			return statement.executeUpdate();
		}
	}

	/**
	 * @return The SQL for updating an entity
	 */
	protected String getUpdateSql() {
		return "UPDATE " + getTableName() + " SET name = ? WHERE code = ?";
	}

	/**
	 * @return The SQL for selecting an entity
	 */
	protected String getSelectSql() {
		return "SELECT id, code, name FROM " + getTableName();
	}

	/**
	 * @return The SQL for registering an entity
	 */
	protected abstract String getRegisterSql();

	protected abstract void setRegisterParameters(PreparedStatement statement, T entity) throws Exception;
}
