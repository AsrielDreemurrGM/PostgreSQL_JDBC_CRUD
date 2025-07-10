package br.com.eaugusto.dao.generics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.eaugusto.annotations.Column;
import br.com.eaugusto.annotations.Table;
import br.com.eaugusto.domain.IPersistable;
import br.com.eaugusto.generic.jdbc.ConnectionFactory;

/**
 * Generic DAO base class for common database operations using annotations and
 * reflection.
 *
 * <p>
 * Automatically maps database rows to entity fields annotated with
 * {@link Column}, and retrieves table names from the {@link Table} annotation.
 * </p>
 *
 * <p>
 * Subclasses only need to implement SQL for registering and updating, and
 * provide the entity class via {@link #getEntityClass()}.
 * </p>
 *
 * @param <T> Entity type extending {@link IPersistable}
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 6, 2025
 */
public abstract class GenericDAO<T extends IPersistable> implements IGenericDAO<T> {

	/**
	 * Retrieves the database table name from the {@link Table} annotation.
	 * 
	 * @return The table name
	 * @throws RuntimeException If the entity class lacks the {@link Table}
	 *                          annotation
	 */
	protected String getTableName() {
		Table tableAnnotation = getEntityClass().getAnnotation(Table.class);
		if (tableAnnotation != null) {
			return tableAnnotation.value();
		} else {
			throw new RuntimeException(
					"Entity class " + getEntityClass().getSimpleName() + " missing @Table annotation");
		}
	}

	/**
	 * Maps a {@link ResultSet} row to an entity using reflection and annotations.
	 * 
	 * <p>
	 * Fields must be annotated with {@link Column} to be mapped automatically.
	 * </p>
	 * 
	 * @param result The result set containing the row data
	 * @return The mapped entity
	 * @throws Exception If a reflection or SQL error occurs during mapping
	 */
	protected final T mapResult(ResultSet result) throws Exception {
		T entity = getEntityClass().getDeclaredConstructor().newInstance();

		for (Field field : getEntityClass().getDeclaredFields()) {
			Column columnAnnotation = field.getAnnotation(Column.class);
			if (columnAnnotation != null) {
				String columnName = columnAnnotation.value();
				Object value = null;
				Class<?> type = field.getType();

				if (type.equals(Long.class)) {
					value = result.getLong(columnName);
				} else if (type.equals(String.class)) {
					value = result.getString(columnName);
				} else if (type.equals(Double.class)) {
					value = result.getDouble(columnName);
				} else if (type.equals(Integer.class)) {
					value = result.getInt(columnName);
				} else {
					throw new RuntimeException("Unsupported field type: " + type.getName());
				}

				String setterName = "set" + Character.toUpperCase(field.getName().charAt(0))
						+ field.getName().substring(1);

				Method setter = getEntityClass().getMethod(setterName, type);
				setter.invoke(entity, value);
			}
		}

		return entity;
	}

	/**
	 * Sets the parameters for updating an entity in the database.
	 * 
	 * @param statement The prepared statement to set parameters on
	 * @param entity    The entity with updated data
	 * @throws Exception If an error occurs while setting parameters
	 */
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

	protected abstract Class<T> getEntityClass();

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
	 * @return The SQL statement for inserting an entity.
	 */
	protected abstract String getRegisterSql();

	/**
	 * Sets the parameters for inserting an entity into the database.
	 * 
	 * @param statement The prepared statement to set parameters on
	 * @param entity    The entity to insert
	 * @throws Exception If an error occurs while setting parameters
	 */
	protected abstract void setRegisterParameters(PreparedStatement statement, T entity) throws Exception;
}
