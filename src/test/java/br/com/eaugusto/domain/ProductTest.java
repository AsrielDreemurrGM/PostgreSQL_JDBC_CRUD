package br.com.eaugusto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.IProductDAO;
import br.com.eaugusto.dao.ProductDAO;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 7, 2025
 */
public class ProductTest {

	private final IProductDAO dao = new ProductDAO();

	@Test
	public void registerTest() throws Exception {
		Product product = new Product();
		product.setCode("A321B654");
		product.setName("Test-Wooden-Table");
		product.setDescription("Four-Legged-Table");

		Integer quantityAdded = dao.register(product);
		assertEquals(1, quantityAdded, "should register one product");

		Product databaseProduct = dao.search(product.getEntityCode());
		assertNotNull(databaseProduct);
		assertNotNull(databaseProduct.getId());
		assertEquals(product.getEntityCode(), databaseProduct.getEntityCode());
		assertEquals(product.getEntityName(), databaseProduct.getEntityName());

		Integer quantityDeleted = dao.delete(databaseProduct);
		assertNotNull(quantityDeleted);
	}

	@Test
	public void searchNullProductTest() throws Exception {
		Product nullProduct = dao.search("This-code-should-never-exist-98764321");
		assertNull(nullProduct);
	}

	@Test
	public void searchAllTest() throws Exception {
		Product product1 = new Product();
		product1.setCode("A987B456");
		product1.setName("Test-Wooden-Cabinet");
		product1.setDescription("Maple-Wood-Cabinet");
		dao.register(product1);

		Product product2 = new Product();
		product2.setCode("A543B210");
		product2.setName("Test-Wooden-Chair");
		product2.setDescription("Three-Legged-Chair");
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
	public void updateTest() throws Exception {
		Product product = new Product();
		product.setCode("A1098B765");
		product.setName("Test-Wood-Couch");
		product.setDescription("Couch-Made-Of-Hard-Wood");
		dao.register(product);

		product.setName("Test-Wooden-Couch");
		Integer updatedRows = dao.update(product);
		assertEquals(1, updatedRows, "one row should be updated");

		Product updatedProduct = dao.search(product.getEntityCode());
		assertEquals("Test-Wooden-Couch", updatedProduct.getEntityName());

		Integer deleted = dao.delete(product);
		assertEquals(1, deleted, "product should be deleted");
	}
}
