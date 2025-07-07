package br.com.eaugusto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.ClientDAO;
import br.com.eaugusto.dao.IClientDAO;

/**
 * Integration test for {@link ClientDAO} using the {@link IClientDAO}
 * interface.
 * 
 * <p>
 * This test performs a complete database cycle for clients: it registers a new
 * client, retrieves it, validates the data, updates it, and then deletes it
 * from the database.
 * </p>
 * 
 * <p>
 * <b>Note:</b> Requires a working database connection and correct environment
 * variables.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public class ClientTest {

	private final IClientDAO dao = new ClientDAO();

	@Test
	public void registerTest() throws Exception {
		Client client = new Client();
		client.setCode("01");
		client.setName("Eduardo");

		Integer quantityAdded = dao.register(client);
		assertEquals(1, quantityAdded, "should register one client");

		Client clientDB = dao.search(client.getEntityCode());
		assertNotNull(clientDB);
		assertNotNull(clientDB.getId());
		assertEquals(client.getEntityCode(), clientDB.getEntityCode());
		assertEquals(client.getEntityName(), clientDB.getEntityName());

		Integer quantityDeleted = dao.delete(clientDB);
		assertNotNull(quantityDeleted);
	}

	@Test
	public void searchNullClientTest() throws Exception {
		Client nullClient = dao.search("This-code-never-should-exist-1234569");
		assertNull(nullClient);
	}

	@Test
	public void searchAllTest() throws Exception {
		Client client1 = new Client();
		client1.setCode("01234");
		client1.setName("Maria");
		dao.register(client1);

		Client client2 = new Client();
		client2.setCode("A3456");
		client2.setName("John");
		dao.register(client2);

		List<Client> clients = dao.searchAll();
		assertNotNull(clients);
		assertEquals(2, clients.size(), "two clients should be found");

		Integer deleted1 = dao.delete(client1);
		assertEquals(1, deleted1, "client1 should be deleted");

		Integer deleted2 = dao.delete(client2);
		assertEquals(1, deleted2, "client2 should be deleted");
	}

	@Test
	public void updateTest() throws Exception {
		Client client = new Client();
		client.setCode("A7654");
		client.setName("Augusto");
		dao.register(client);

		client.setName("Eduardo Augusto");
		Integer updatedRows = dao.update(client);
		assertEquals(1, updatedRows, "one row should be updated");

		Client updatedClient = dao.search(client.getEntityCode());
		assertEquals("Eduardo Augusto", updatedClient.getEntityName());

		Integer deleted = dao.delete(client);
		assertEquals(1, deleted, "client should be deleted");
	}
}
