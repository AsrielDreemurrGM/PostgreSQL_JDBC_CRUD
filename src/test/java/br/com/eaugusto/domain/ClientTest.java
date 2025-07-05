package br.com.eaugusto.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import br.com.eaugusto.dao.ClientDAO;
import br.com.eaugusto.dao.IClientDAO;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public class ClientTest {

	@Test
	public void registerTest() throws Exception {
		IClientDAO dao = new ClientDAO();

		Client client = new Client();
		client.setCode("01");
		client.setName("Eduardo");

		Integer quantityAdded = dao.register(client);
		assertTrue(quantityAdded == 1);

		Client clientDB = dao.search(client.getCode());
		assertNotNull(clientDB);
		assertNotNull(clientDB.getId());
		assertEquals(client.getCode(), clientDB.getCode());
		assertEquals(client.getName(), clientDB.getName());

		Integer quantityDeleted = dao.delete(clientDB);
		assertNotNull(quantityDeleted);
	}
}
