package br.com.eaugusto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.ClientDAO;
import br.com.eaugusto.dao.IClientDAO;
import br.com.eaugusto.exceptions.DAOException;
import br.com.eaugusto.exceptions.DAOParameterException;

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
 * Uses auto-generated SQL queries based on annotations in the domain classes.
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
	public void registerTest() throws DAOException, DAOParameterException {
		Client client = new Client();
		client.setCode("01");
		client.setName("Eduardo");
		client.setEmail("eduardo@example.com");
		client.setPhone("(99) 98765-1234");

		Integer quantityAdded = dao.register(client);
		assertEquals(1, quantityAdded, "should register one client");

		Client clientDB = dao.search(client.getEntityCode());
		assertNotNull(clientDB);
		assertNotNull(clientDB.getId());
		assertEquals(client.getEntityCode(), clientDB.getEntityCode());
		assertEquals(client.getEntityName(), clientDB.getEntityName());
		assertEquals(client.getEmail(), clientDB.getEmail());
		assertEquals(client.getPhone(), clientDB.getPhone());

		Integer quantityDeleted = dao.delete(clientDB);
		assertEquals(1, quantityDeleted, "should delete one client");
	}

	@Test
	public void searchNullClientTest() throws DAOException {
		Client nullClient = dao.search("This-code-never-should-exist-1234569");
		assertNull(nullClient);
	}

	@Test
	public void searchAllTest() throws DAOException, DAOParameterException {
		Client client1 = new Client();
		client1.setCode("01234");
		client1.setName("Maria");
		client1.setEmail("maria@example.com");
		client1.setPhone("(99) 76543-5678");
		dao.register(client1);

		Client client2 = new Client();
		client2.setCode("A3456");
		client2.setName("John");
		client2.setEmail("john@example.com");
		client2.setPhone("(99) 43210-8765");
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
	public void updateTest() throws DAOException, DAOParameterException {
		Client client = new Client();
		client.setCode("A7654");
		client.setName("Augusto");
		client.setEmail("augusto@example.com");
		client.setPhone("(88) 00000-0000");
		dao.register(client);

		client.setName("Eduardo Augusto");
		client.setEmail("eduardo.augusto@example.com");
		client.setPhone("(99) 45678-8765");
		Integer updatedRows = dao.update(client);
		assertEquals(1, updatedRows, "one row should be updated");

		Client updatedClient = dao.search(client.getEntityCode());
		assertEquals("Eduardo Augusto", updatedClient.getEntityName());
		assertEquals("eduardo.augusto@example.com", updatedClient.getEmail());
		assertEquals("(99) 45678-8765", updatedClient.getPhone());

		Integer deleted = dao.delete(client);
		assertEquals(1, deleted, "client should be deleted");
	}
}
