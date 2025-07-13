package br.com.eaugusto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.eaugusto.domain.Inventory;
import br.com.eaugusto.exceptions.DAOException;
import br.com.eaugusto.exceptions.InventoryMappingException;
import br.com.eaugusto.generic.jdbc.ConnectionFactory;

/**
 * Inventory DAO implementation that provides methods to register and query
 * inventory transactions in the database.
 * 
 * <p>
 * It handles manual SQL interactions using JDBC and maps the results to the
 * {@link Inventory} entity. It uses a custom exception,
 * {@link InventoryMappingException}, for handling mapping errors.
 * </p>
 * 
 * @see IInventoryDAO
 * @see Inventory
 * @see DAOException
 * @see InventoryMappingException
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 12, 2025
 */
public class InventoryDAO implements IInventoryDAO {

	@Override
	public Integer register(Inventory inventory) {
		String sql = """
				INSERT INTO tb_inventory (id, client_id, product_id, quantity_sold, sale_date)
				VALUES (nextval('sq_inventory'), ?, ?, ?, CURRENT_TIMESTAMP)
				""";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, inventory.getClientId());
			statement.setLong(2, inventory.getProductId());
			statement.setInt(3, inventory.getQuantitySold());

			return statement.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("Error registering inventory transaction", e);
		}
	}

	@Override
	public List<Inventory> searchAll() {
		String sql = """
				SELECT id, client_id, product_id, quantity_sold, sale_date
				FROM tb_inventory
				""";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet result = statement.executeQuery()) {

			List<Inventory> inventoryList = new ArrayList<>();

			while (result.next()) {
				Inventory inventoryItem = buildFromResult(result);
				inventoryList.add(inventoryItem);
			}
			return inventoryList;
		} catch (Exception e) {
			throw new DAOException("Error retrieving all inventory entries", e);
		}
	}

	@Override
	public List<Inventory> searchByClient(Long clientId) {
		String sql = """
				SELECT id, client_id, product_id, quantity_sold, sale_date
				FROM tb_inventory
				WHERE client_id = ?
				""";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, clientId);
			try (ResultSet result = statement.executeQuery()) {

				List<Inventory> inventoryList = new ArrayList<>();
				while (result.next()) {
					Inventory inventoryItem = buildFromResult(result);
					inventoryList.add(inventoryItem);
				}
				return inventoryList;
			}

		} catch (Exception e) {
			throw new DAOException("Error retrieving inventory by client ID", e);
		}
	}

	@Override
	public List<Inventory> searchByProduct(Long productId) {
		String sql = """
				SELECT id, client_id, product_id, quantity_sold, sale_date
				FROM tb_inventory WHERE product_id = ?
				""";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, productId);
			try (ResultSet result = statement.executeQuery()) {

				List<Inventory> inventoryList = new ArrayList<>();
				while (result.next()) {
					Inventory inventoryItem = buildFromResult(result);
					inventoryList.add(inventoryItem);
				}
				return inventoryList;
			}

		} catch (Exception e) {
			throw new DAOException("Error retrieving inventory by product ID", e);
		}
	}

	@Override
	public Integer deleteById(Long id) {
		String sql = "DELETE FROM tb_inventory WHERE id = ?";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("Error deleting inventory item by ID", e);
		}
	}

	/**
	 * Builds and returns an {@link Inventory} object from a database result row.
	 * 
	 * @param result The current row of the {@link ResultSet}
	 * @return An {@link Inventory} object built from the row
	 * @throws InventoryMappingException If any column cannot be mapped properly
	 */
	private Inventory buildFromResult(ResultSet result) throws InventoryMappingException {
		try {
			Inventory inventory = new Inventory();

			inventory.setId(result.getLong("id"));
			inventory.setClientId(result.getLong("client_id"));
			inventory.setProductId(result.getLong("product_id"));
			inventory.setQuantitySold(result.getInt("quantity_sold"));
			inventory.setSaleDate(result.getTimestamp("sale_date"));

			return inventory;
		} catch (Exception e) {
			throw new InventoryMappingException("Error mapping inventory result", e);
		}
	}
}
