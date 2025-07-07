package br.com.eaugusto.domain;

/**
 * Interface to be implemented by all domain entities that can be persisted to
 * the database.
 * <p>
 * Provides methods to retrieve the entity's unique code and name, enabling
 * generic DAO operations.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 6, 2025
 */
public interface IPersistable {

	public String getEntityCode();

	public String getEntityName();
}
