package br.com.eaugusto.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.eaugusto.dao.generics.GenericDAO;
import br.com.eaugusto.domain.Product;
import br.com.eaugusto.exceptions.DAOParameterException;

/**
 * DAO implementation for {@link Product} entities using {@link GenericDAO}.
 *
 * <p>
 * SQL statements are automatically generated based on entity annotations. This
 * class provides parameter mappings for product-specific operations.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 7, 2025 (updated July 10, 2025 for SQL refactoring)
 */
public class ProductDAO extends GenericDAO<Product> implements IProductDAO {

	@Override
	protected void setUpdateParameters(PreparedStatement statement, Product entity) throws DAOParameterException {
		try {
			statement.setString(1, entity.getEntityName());
			statement.setString(2, entity.getDescription());
			statement.setDouble(3, entity.getPrice());
			statement.setInt(4, entity.getStockQuantity());
			statement.setString(5, entity.getEntityCode());
		} catch (SQLException e) {
			throw new DAOParameterException("Error setting register parameters for Client.", e);
		}
	}

	@Override
	protected void setRegisterParameters(PreparedStatement statement, Product entity) throws DAOParameterException {
		try {
			statement.setString(1, entity.getEntityCode());
			statement.setString(2, entity.getEntityName());
			statement.setString(3, entity.getDescription());
			statement.setDouble(4, entity.getPrice());
			statement.setInt(5, entity.getStockQuantity());
		} catch (SQLException e) {
			throw new DAOParameterException("Error setting register parameters for Client.", e);
		}
	}

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}
}
