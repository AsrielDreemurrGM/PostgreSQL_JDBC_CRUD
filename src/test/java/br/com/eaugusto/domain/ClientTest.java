package br.com.eaugusto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.ClientDAO;
import br.com.eaugusto.dao.IClientDAO;
import br.com.eaugusto.exceptions.DAOException;
import br.com.eaugusto.exceptions.DAOParameterException;

/**
 * Integration test suite for the {@link ClientDAO} operations.
 * 
 * <p>
 * Covers registering, searching, updating, deleting, and listing all clients
 * from the database. Validates data integrity and CRUD behavior.
 * </p>
 * 
 * <p>
 * Uses annotation-based SQL generation and requires a functional database
 * connection for execution.
 * </p>
 * 
 * <p>
 * <b>Note:</b> Requires a working database connection and correct environment
 * variables.
 * </p>
 * 
 * @see IClientDAO
 * @see DAOException
 * @see DAOParameterException
 * @see br.com.eaugusto.domain.Client
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public class ClientTest {

	private final IClientDAO dao = new ClientDAO();

	@Test
	public void registerTest() throws DAOException, DAOParameterException {
		Client client = new Client();
		client.setCode("C1001");
		client.setName("Eduardo");
		client.setCpf("12345678900");
		client.setPhone("(11) 99999-1234");
		client.setAddress("Rua dos Testes");
		client.setAddressNumber("101");
		client.setCity("JDBC");
		client.setState("Spring");
		client.setBirthDate(LocalDate.parse("2005-04-01"));

		Integer quantityAdded = dao.register(client);
		assertEquals(1, quantityAdded, "should register one client");

		Client clientDB = dao.search(client.getEntityCode());
		assertNotNull(clientDB);
		assertEquals("Eduardo", clientDB.getEntityName());
		assertEquals("12345678900", clientDB.getCpf());
		assertEquals("Rua dos Testes", clientDB.getAddress());
		assertEquals("101", clientDB.getAddressNumber());
		assertEquals("JDBC", clientDB.getCity());
		assertEquals("Spring", clientDB.getState());
		assertEquals(LocalDate.parse("2005-04-01"), clientDB.getBirthDate());

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
		client1.setCode("C1002");
		client1.setName("Maria");
		client1.setCpf("11111111111");
		client1.setPhone("(11) 12345-6789");
		client1.setAddress("Rua A");
		client1.setAddressNumber("22");
		client1.setCity("Java");
		client1.setState("Oracle");
		client1.setBirthDate(LocalDate.parse("2002-03-10"));
		dao.register(client1);

		Client client2 = new Client();
		client2.setCode("C1003");
		client2.setName("João");
		client2.setCpf("22222222222");
		client2.setPhone("(11) 98765-4321");
		client2.setAddress("Rua B");
		client2.setAddressNumber("33");
		client2.setCity("Select");
		client2.setState("SQL");
		client2.setBirthDate(LocalDate.parse("2000-07-20"));
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
		client.setCode("C1004");
		client.setName("Lucas");
		client.setCpf("33333333333");
		client.setPhone("123456789");
		client.setAddress("Rua Java");
		client.setAddressNumber("12");
		client.setCity("HelloWorld");
		client.setState("Sun");
		client.setBirthDate(LocalDate.parse("1995-05-05"));
		dao.register(client);

		client.setName("Lucas Silva");
		client.setPhone("987654321");
		client.setAddress("Rua SQL");
		client.setAddressNumber("45");
		client.setCity("Query");
		client.setState("PostgreSQL");
		client.setBirthDate(LocalDate.parse("1999-06-06"));

		Integer updatedRows = dao.update(client);
		assertEquals(1, updatedRows, "one row should be updated");

		Client updated = dao.search(client.getEntityCode());
		assertEquals("Lucas Silva", updated.getEntityName());
		assertEquals("987654321", updated.getPhone());
		assertEquals("Rua SQL", updated.getAddress());
		assertEquals("45", updated.getAddressNumber());
		assertEquals("Query", updated.getCity());
		assertEquals("PostgreSQL", updated.getState());
		assertEquals(LocalDate.parse("1999-06-06"), updated.getBirthDate());

		Integer deleted = dao.delete(client);
		assertEquals(1, deleted, "client should be deleted");
	}
}
