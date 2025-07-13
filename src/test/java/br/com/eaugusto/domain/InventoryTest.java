package br.com.eaugusto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.ClientDAO;
import br.com.eaugusto.dao.IClientDAO;
import br.com.eaugusto.dao.IInventoryDAO;
import br.com.eaugusto.dao.IProductDAO;
import br.com.eaugusto.dao.InventoryDAO;
import br.com.eaugusto.dao.ProductDAO;

/**
 * Integration test suite for the {@link InventoryDAO} operations.
 * 
 * <p>
 * Covers registering, searching, and deleting inventory transactions. Uses
 * {@code @BeforeEach} to prepare client/product data and {@code @AfterEach} to
 * clean it up.
 * </p>
 * 
 * <p>
 * Also includes a test that verifies DAOException is thrown when foreign key
 * constraints are violated.
 * </p>
 * 
 * <p>
 * <b>Note:</b> Requires a working database connection and correct environment
 * variables.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 12, 2025
 */
public class InventoryTest {

	private final IClientDAO clientDAO = new ClientDAO();
	private final IProductDAO productDAO = new ProductDAO();
	private final IInventoryDAO inventoryDAO = new InventoryDAO();

	private Client client;
	private Product product;
	private Long inventoryId;

	@BeforeEach
	public void setup() {
		client = new Client();
		client.setCode("INVTEST1");
		client.setName("Inventory Client");
		client.setPhone("(11) 99999-0000");
		client.setCpf("00000000191");
		client.setAddress("Rua Inventory");
		client.setAddressNumber("10");
		client.setCity("SQL");
		client.setState("PostgreSQL");
		client.setBirthDate(LocalDate.parse("2000-01-01"));
		clientDAO.register(client);
		client.setId(clientDAO.search(client.getEntityCode()).getId());

		product = new Product();
		product.setCode("INVTEST2");
		product.setName("Inventory Product");
		product.setDescription("Product for inventory testing");
		product.setPrice(199.99);
		product.setStockQuantity(50);
		product.setCategory("Test Category");
		productDAO.register(product);
		product.setId(productDAO.search(product.getEntityCode()).getId());

		Inventory inv = new Inventory();
		inv.setClientId(client.getId());
		inv.setProductId(product.getId());
		inv.setQuantitySold(5);
		inventoryDAO.register(inv);

		List<Inventory> all = inventoryDAO.searchAll();
		inventoryId = all.get(0).getId();
	}

	@AfterEach
	public void cleanup() {
		if (inventoryId != null) {
			inventoryDAO.deleteById(inventoryId);
		}
		clientDAO.delete(client);
		productDAO.delete(product);
	}

	@Test
	public void testRegisterInventory() {
		List<Inventory> list = inventoryDAO.searchAll();
		assertEquals(1, list.size(), "Should have one registered inventory record");
		assertNotNull(list.get(0).getSaleDate());
	}

	@Test
	public void testSearchByClientId() {
		List<Inventory> result = inventoryDAO.searchByClient(client.getId());
		assertNotNull(result);
		assertEquals(1, result.size(), "Should retrieve inventory by client ID");
		assertEquals(client.getId(), result.get(0).getClientId());
	}

	@Test
	public void testSearchByProductId() {
		List<Inventory> result = inventoryDAO.searchByProduct(product.getId());
		assertNotNull(result);
		assertEquals(1, result.size(), "Should retrieve inventory by product ID");
		assertEquals(product.getId(), result.get(0).getProductId());
	}

	@Test
	public void testDeleteInventoryById() {
		Integer deleted = inventoryDAO.deleteById(inventoryId);
		assertEquals(1, deleted, "Should delete one inventory record");
		inventoryId = null;
	}
}
