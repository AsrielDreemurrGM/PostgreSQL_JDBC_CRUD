package br.com.eaugusto.databasetest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import br.com.eaugusto.generic.jdbc.ConnectionFactory;

/**
 * Unit test for {@link ConnectionFactory}.
 *
 * Tests whether a connection to the database can be established using
 * environment variables.
 *
 * NOTE: Ensure DB_URL, DB_USERNAME, and DB_PASSWORD are correctly set before
 * running.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public class DatabaseConnectionTest {

	@Test
	void testDatabaseConnection() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();

		assertNotNull(connection, "Connection should not be null.");
		assertTrue(connection.isValid(2), "Connection should be valid.");

		connection.close();
	}
}
