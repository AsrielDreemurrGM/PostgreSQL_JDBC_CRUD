package br.com.eaugusto.dao;

import java.sql.PreparedStatement;

import br.com.eaugusto.dao.generics.GenericDAO;
import br.com.eaugusto.domain.Product;

/**
 * DAO implementation for {@link Product} entities using {@link GenericDAO}.
 * 
 * <p>
 * Provides SQL statements and parameter mappings for product-specific
 * operations. Entity mapping is handled automatically by the generic superclass
 * using annotations.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 7, 2025
 */
public class ProductDAO extends GenericDAO<Product> implements IProductDAO {

	@Override
	protected void setUpdateParameters(PreparedStatement statement, Product entity) throws Exception {
		statement.setString(1, entity.getEntityName());
		statement.setString(2, entity.getDescription());
		statement.setDouble(3, entity.getPrice());
		statement.setInt(4, entity.getStockQuantity());
		statement.setString(5, entity.getEntityCode());
	}

	@Override
	protected void setRegisterParameters(PreparedStatement statement, Product entity) throws Exception {
		statement.setString(1, entity.getEntityCode());
		statement.setString(2, entity.getEntityName());
		statement.setString(3, entity.getDescription());
		statement.setDouble(4, entity.getPrice());
		statement.setInt(5, entity.getStockQuantity());
	}

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}
}
