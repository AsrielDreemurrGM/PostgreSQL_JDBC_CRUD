package br.com.eaugusto.dao.generics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.eaugusto.annotations.Column;
import br.com.eaugusto.annotations.Table;
import br.com.eaugusto.domain.IPersistable;
import br.com.eaugusto.exceptions.DAOException;
import br.com.eaugusto.exceptions.DAOParameterException;
import br.com.eaugusto.exceptions.EntityMappingException;
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
 * This class automatically generates SQL statements for registering, selecting,
 * and updating entities based on annotations. Subclasses only need to provide
 * entity-specific parameter mappings and the entity class via
 * {@link #getEntityClass()}.
 * </p>
 *
 * @param <T> Entity type extending {@link IPersistable}
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 6, 2025
 */
public abstract class GenericDAO<T extends IPersistable> implements IGenericDAO<T> {

	private static final String WHERECODE = " WHERE code = ?";

	/**
	 * Retrieves the database table name from the {@link Table} annotation.
	 *
	 * @return The table name
	 * @throws RuntimeException If the entity class lacks the {@link Table}
	 *                          annotation
	 */
	private final String getTableName() {
		Table tableAnnotation = getEntityClass().getAnnotation(Table.class);
		if (tableAnnotation == null) {
			throw new EntityMappingException(
					"Entity class " + getEntityClass().getSimpleName() + " missing @Table annotation");
		}
		return tableAnnotation.value();
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
	protected final T mapResult(ResultSet result) {
		try {
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
					} else if (type.equals(LocalDate.class)) {
						java.sql.Date sqlDate = result.getDate(columnName);
						value = sqlDate != null ? sqlDate.toLocalDate() : null;
					} else {
						throw new EntityMappingException("Unsupported field type: " + type.getName());
					}

					String setterName = "set" + Character.toUpperCase(field.getName().charAt(0))
							+ field.getName().substring(1);

					Method setter = getEntityClass().getMethod(setterName, type);
					setter.invoke(entity, value);
				}
			}

			return entity;
		} catch (Exception e) {
			throw new EntityMappingException("Failed to map entity: " + getEntityClass().getSimpleName(), e);
		}
	}

	/**
	 * Sets the parameters for updating an entity in the database.
	 * 
	 * @param statement The prepared statement to set parameters on
	 * @param entity    The entity with updated data
	 * @throws Exception If an error occurs while setting parameters
	 */
	protected abstract void setUpdateParameters(PreparedStatement statement, T entity) throws DAOParameterException;

	@Override
	public Integer register(T entity) {
		String sql = getRegisterSql();

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			setRegisterParameters(statement, entity);

			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error registering " + getEntityClass().getSimpleName(), e);
		}
	}

	@Override
	public T search(String code) {
		String sql = getSelectSql() + WHERECODE;

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, code);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					return mapResult(result);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Error searching entity by code: " + getEntityClass().getSimpleName(), e);
		}
		return null;
	}

	@Override
	public Integer delete(T entity) {
		String sql = "DELETE FROM " + getTableName() + WHERECODE;

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, entity.getEntityCode());

			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error deleting entity: " + getEntityClass().getSimpleName(), e);
		}
	}

	@Override
	public List<T> searchAll() {
		String sql = getSelectSql();

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet result = statement.executeQuery()) {

			List<T> entities = new ArrayList<>();
			while (result.next()) {
				entities.add(mapResult(result));
			}
			return entities;
		} catch (SQLException e) {
			throw new DAOException("Error retrieving all entities: " + getEntityClass().getSimpleName(), e);
		}
	}

	@Override
	public Integer update(T entity) {
		String sql = getUpdateSql();

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			setUpdateParameters(statement, entity);
			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error updating entity: " + getEntityClass().getSimpleName(), e);
		}
	}

	protected abstract Class<T> getEntityClass();

	/**
	 * Generates the SQL statement for updating an entity based on its annotated
	 * fields.
	 *
	 * @return The SQL update statement.
	 */
	protected String getUpdateSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(getTableName()).append(" SET ");

		List<String> columns = new ArrayList<>();
		for (Field field : getEntityClass().getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null && !"id".equalsIgnoreCase(column.value()) && !"code".equalsIgnoreCase(column.value())) {
				columns.add(column.value() + " = ?");
			}
		}

		sql.append(String.join(", ", columns)).append(WHERECODE);
		return sql.toString();
	}

	/**
	 * Generates the SQL statement for selecting entities based on annotated fields.
	 *
	 * @return The SQL select statement.
	 */
	protected String getSelectSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");

		List<String> columns = new ArrayList<>();
		for (Field field : getEntityClass().getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				columns.add(column.value());
			}
		}

		sql.append(String.join(", ", columns)).append(" FROM ").append(getTableName());
		return sql.toString();
	}

	/**
	 * Generates the SQL statement for inserting a new entity based on its annotated
	 * fields.
	 *
	 * @return The SQL insert statement.
	 */
	protected String getRegisterSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ").append(getTableName()).append(" (id");

		List<String> columns = new ArrayList<>();
		for (Field field : getEntityClass().getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null && !"id".equalsIgnoreCase(column.value())) {
				columns.add(column.value());
			}
		}

		for (String column : columns) {
			sql.append(", ").append(column);
		}
		sql.append(") VALUES (nextval('sq_").append(getTableName().substring(3)).append("')");

		for (int i = 0; i < columns.size(); i++) {
			sql.append(", ?");
		}
		sql.append(")");

		return sql.toString();
	}

	/**
	 * Sets the parameters for inserting an entity into the database.
	 * 
	 * @param statement The prepared statement to set parameters on
	 * @param entity    The entity to insert
	 * @throws Exception If an error occurs while setting parameters
	 */
	protected abstract void setRegisterParameters(PreparedStatement statement, T entity) throws DAOParameterException;
}
