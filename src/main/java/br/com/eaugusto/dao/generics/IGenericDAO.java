package br.com.eaugusto.dao.generics;

import java.util.List;

import br.com.eaugusto.domain.IPersistable;

/**
 * Generic DAO interface that defines basic CRUD operations for persistent
 * entities.
 * 
 * <p>
 * This interface provides methods for registering, searching, updating,
 * deleting, and retrieving all entities from the database. It is designed to be
 * implemented by DAOs handling specific entity types.
 * </p>
 * 
 * @param <T> The entity type, which must implement {@link IPersistable}
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
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

	/**
	 * Retrieves all entities from the database.
	 * 
	 * @return A list of all entities
	 * @throws Exception If a database error occurs
	 */
	public List<T> searchAll() throws Exception;

	/**
	 * Updates an existing entity in the database.
	 * 
	 * @param entity The entity to update
	 * @return The number of rows affected
	 * @throws Exception If a database error occurs
	 */
	public Integer update(T entity) throws Exception;
}
