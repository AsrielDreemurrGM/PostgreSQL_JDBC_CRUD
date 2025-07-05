package br.com.eaugusto.generic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class that manages a single JDBC connection to the PostgreSQL
 * database.
 * 
 * <p>
 * Connection details are read from environment variables:
 * <ul>
 * <li><b>DB_URL</b>: JDBC URL for the database</li>
 * <li><b>DB_USERNAME</b>: Username for the database</li>
 * <li><b>DB_PASSWORD</b>: Password for the database</li>
 * </ul>
 * </p>
 * 
 * <p>
 * The connection is created only when needed and reused until closed. If the
 * connection closes, a new one will be created automatically.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public final class ConnectionFactory {

	private static Connection connection;

	private ConnectionFactory() {
		// To prevent instantiation
	}

	/**
	 * Provides a singleton JDBC connection to the configured database.
	 * 
	 * @return an open {@link Connection}
	 * @throws SQLException          if a database access error occurs or the
	 *                               connection cannot be established
	 * @throws IllegalStateException if required environment variables are not set
	 */
	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = initConnection();
		}
		return connection;
	}

	/**
	 * Initializes a new database connection using environment variables.
	 * 
	 * @return a new {@link Connection}
	 * @throws RuntimeException wrapping any {@link SQLException} that occurs
	 */
	private static Connection initConnection() {
		try {
			String url = System.getenv("DB_URL");
			String username = System.getenv("DB_USERNAME");
			String password = System.getenv("DB_PASSWORD");

			if (url == null || username == null || password == null) {
				throw new IllegalStateException("Database environment variables are not set.");
			}

			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to connect to the database.", e);
		}
	}
}
