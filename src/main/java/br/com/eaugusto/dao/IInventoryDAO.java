package br.com.eaugusto.dao;

import java.util.List;

import br.com.eaugusto.domain.Inventory;

/**
 * Inventory DAO interface for managing inventory transactions.
 * 
 * <p>
 * Provides methods for registering new transactions, retrieving entries by
 * client or product, listing all transactions, and deleting entries by ID.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 12, 2025
 */
public interface IInventoryDAO {

	/**
	 * Registers a new inventory transaction in the database.
	 * 
	 * @param inventory The inventory record to register
	 * @return The number of rows affected
	 */
	public Integer register(Inventory inventory);

	/**
	 * Retrieves all inventory transactions from the database.
	 * 
	 * @return A list of all inventory records
	 */
	public List<Inventory> searchAll();

	/**
	 * Retrieves all inventory transactions by a given client ID.
	 * 
	 * @param clientId The client ID
	 * @return A list of inventory records linked to the client
	 */
	public List<Inventory> searchByClient(Long clientId);

	/**
	 * Retrieves all inventory transactions by a given product ID.
	 * 
	 * @param productId The product ID
	 * @return A list of inventory records linked to the product
	 */
	public List<Inventory> searchByProduct(Long productId);

	/**
	 * Deletes an inventory record by its ID.
	 * 
	 * @param id The inventory record ID
	 * @return The number of rows affected
	 */
	public Integer deleteById(Long id);
}
