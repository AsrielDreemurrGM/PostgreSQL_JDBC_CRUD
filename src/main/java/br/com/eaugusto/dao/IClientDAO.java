package br.com.eaugusto.dao;

import java.util.List;

import br.com.eaugusto.domain.Client;

/**
 * DAO interface for client-related database operations. Defines the standard
 * methods for registering, searching, and deleting clients from the database.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public interface IClientDAO {

	/**
	 * Registers a new client in the database.
	 * 
	 * @param client The client to register
	 * @return The number of rows affected
	 * @throws Exception If a database error occurs
	 */
	public Integer register(Client client) throws Exception;

	/**
	 * Searches for a client by their unique code.
	 * 
	 * @param code The code of the client to search
	 * @return The found client, or null if not found
	 * @throws Exception If a database error occurs
	 */
	public Client search(String code) throws Exception;

	/**
	 * Deletes a client from the database.
	 * 
	 * @param clientDB The client to delete
	 * @return The number of rows affected
	 * @throws Exception If a database error occurs
	 */
	public Integer delete(Client clientDB) throws Exception;

	public List<Client> searchAll() throws Exception;

	public Integer update(Client client) throws Exception;
}
