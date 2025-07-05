package br.com.eaugusto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.eaugusto.domain.Client;
import br.com.eaugusto.generic.jdbc.ConnectionFactory;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public class ClientDAO implements IClientDAO {

	@Override
	public Integer register(Client client) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO tb_client (id, code, name) VALUES (nextval('sq_client'), ?, ?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, client.getCode());
			statement.setString(2, client.getName());

			return statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Client search(String code) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Client client = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM tb_client WHERE code = ?";
			statement = connection.prepareStatement(sql);

			statement.setString(1, code);

			result = statement.executeQuery();

			if (result.next()) {
				client = new Client();
				client.setId(result.getLong("id"));
				client.setCode(result.getString("code"));
				client.setName(result.getString("name"));
			}
			return client;
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Integer delete(Client client) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM tb_client WHERE code = ?";
			statement = connection.prepareStatement(sql);

			statement.setString(1, client.getCode());

			return statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}
}
