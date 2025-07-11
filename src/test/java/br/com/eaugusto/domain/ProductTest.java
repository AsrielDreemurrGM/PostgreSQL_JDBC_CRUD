package br.com.eaugusto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.IProductDAO;
import br.com.eaugusto.dao.ProductDAO;
import br.com.eaugusto.exceptions.DAOException;
import br.com.eaugusto.exceptions.DAOParameterException;

/**
 * Integration test for {@link ProductDAO} using the {@link IProductDAO}
 * interface.
 * 
 * <p>
 * This test performs a complete database cycle for products: it registers a new
 * product, retrieves it, validates the data, updates it, and then deletes it
 * from the database.
 * </p>
 * 
 * <p>
 * Uses auto-generated SQL queries based on annotations in the domain classes.
 * </p>
 * 
 * <p>
 * <b>Note:</b> Requires a working database connection and correct environment
 * variables.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 7, 2025
 */
public class ProductTest {

	private final IProductDAO dao = new ProductDAO();

	@Test
	public void registerTest() throws DAOException, DAOParameterException {
		Product product = new Product();
		product.setCode("A321B654");
		product.setName("Test-Wooden-Table");
		product.setDescription("Four-Legged-Table");
		product.setPrice(99.99);
		product.setStockQuantity(10);

		Integer quantityAdded = dao.register(product);
		assertEquals(1, quantityAdded, "should register one product");

		Product databaseProduct = dao.search(product.getEntityCode());
		assertNotNull(databaseProduct);
		assertNotNull(databaseProduct.getId());
		assertEquals(product.getEntityCode(), databaseProduct.getEntityCode());
		assertEquals(product.getEntityName(), databaseProduct.getEntityName());
		assertEquals(product.getDescription(), databaseProduct.getDescription());
		assertEquals(product.getPrice(), databaseProduct.getPrice());
		assertEquals(product.getStockQuantity(), databaseProduct.getStockQuantity());

		Integer quantityDeleted = dao.delete(databaseProduct);
		assertEquals(1, quantityDeleted, "should delete one product");
	}

	@Test
	public void searchNullProductTest() throws DAOException {
		Product nullProduct = dao.search("This-code-should-never-exist-98764321");
		assertNull(nullProduct);
	}

	@Test
	public void searchAllTest() throws DAOException, DAOParameterException {
		Product product1 = new Product();
		product1.setCode("A987B456");
		product1.setName("Test-Wooden-Cabinet");
		product1.setDescription("Maple-Wood-Cabinet");
		product1.setPrice(150.00);
		product1.setStockQuantity(5);
		dao.register(product1);

		Product product2 = new Product();
		product2.setCode("A543B210");
		product2.setName("Test-Wooden-Chair");
		product2.setDescription("Three-Legged-Chair");
		product2.setPrice(49.99);
		product2.setStockQuantity(15);
		dao.register(product2);

		List<Product> products = dao.searchAll();
		assertNotNull(products);
		assertEquals(2, products.size(), "two products should be found");

		Integer deleted1 = dao.delete(product1);
		assertEquals(1, deleted1, "product1 should be deleted");

		Integer deleted2 = dao.delete(product2);
		assertEquals(1, deleted2, "product2 should be deleted");
	}

	@Test
	public void updateTest() throws DAOException, DAOParameterException {
		Product product = new Product();
		product.setCode("A1098B765");
		product.setName("Test-Wood-Couch");
		product.setDescription("Couch-Made-Of-Hard-Wood");
		product.setPrice(200.00);
		product.setStockQuantity(7);
		dao.register(product);

		product.setName("Test-Wooden-Couch");
		product.setPrice(210.00);
		product.setStockQuantity(9);
		Integer updatedRows = dao.update(product);
		assertEquals(1, updatedRows, "one row should be updated");

		Product updatedProduct = dao.search(product.getEntityCode());
		assertEquals("Test-Wooden-Couch", updatedProduct.getEntityName());
		assertEquals(210.00, updatedProduct.getPrice());
		assertEquals(9, updatedProduct.getStockQuantity());

		Integer deleted = dao.delete(product);
		assertEquals(1, deleted, "product should be deleted");
	}
}
