package br.com.eaugusto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.eaugusto.domain.Client;
import br.com.eaugusto.generic.jdbc.ConnectionFactory;

/**
 * DAO implementation for Client using JDBC and PostgreSQL.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public class ClientDAO implements IClientDAO {

	@Override
	public Integer register(Client client) throws Exception {
		String sql = "INSERT INTO tb_client (id, code, name) VALUES (nextval('sq_client'), ?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, client.getCode());
			statement.setString(2, client.getName());

			return statement.executeUpdate();
		}
	}

	@Override
	public Client search(String code) throws Exception {
		String sql = "SELECT id, code, name FROM tb_client WHERE code = ?";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, code);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					Client client = new Client();
					client.setId(result.getLong("id"));
					client.setCode(result.getString("code"));
					client.setName(result.getString("name"));
					return client;
				}
			}
		}
		return null;
	}

	@Override
	public Integer delete(Client client) throws Exception {
		String sql = "DELETE FROM tb_client WHERE code = ?";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, client.getCode());

			return statement.executeUpdate();
		}
	}
}
