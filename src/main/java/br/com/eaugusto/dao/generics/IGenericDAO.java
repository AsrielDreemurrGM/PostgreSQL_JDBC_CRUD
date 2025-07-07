package br.com.eaugusto.dao.generics;

import java.util.List;

import br.com.eaugusto.domain.IPersistable;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @param <T>
 * @since July 6, 2025
 */
public interface IGenericDAO<T extends IPersistable> {

	/**
	 * Registers a new entity in the database.
	 * 
	 * @param entity The entity to register
	 * @return The number of rows affected
	 * @throws Exception If a database error occurs
	 */
	public Integer register(T entity) throws Exception;

	/**
	 * Searches for a entity by their unique code.
	 * 
	 * @param code The code of the entity to search
	 * @return The found entity, or null if not found
	 * @throws Exception If a database error occurs
	 */
	public T search(String code) throws Exception;

	/**
	 * Deletes a entity from the database.
	 * 
	 * @param databaseEntity The entity to delete
	 * @return The number of rows affected
	 * @throws Exception If a database error occurs
	 */
	public Integer delete(T databaseEntity) throws Exception;

	public List<T> searchAll() throws Exception;

	public Integer update(T entity) throws Exception;
}
