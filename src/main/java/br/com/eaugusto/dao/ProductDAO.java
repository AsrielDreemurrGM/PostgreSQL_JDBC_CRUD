package br.com.eaugusto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.eaugusto.dao.generics.GenericDAO;
import br.com.eaugusto.domain.Product;

/**
 * DAO implementation for {@link Product} entities using {@link GenericDAO}.
 * 
 * <p>
 * Provides database operations including custom handling for the "description"
 * field.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 7, 2025
 */
public class ProductDAO extends GenericDAO<Product> implements IProductDAO {

	@Override
	protected String getTableName() {
		return "tb_product";
	}

	@Override
	protected Product mapResult(ResultSet result) throws Exception {
		Product product = new Product();
		product.setId(result.getLong("id"));
		product.setCode(result.getString("code"));
		product.setName(result.getString("name"));
		product.setDescription(result.getString("description"));
		product.setPrice(result.getDouble("price"));
		product.setStockQuantity(result.getInt("stock_quantity"));
		return product;
	}

	@Override
	protected void setUpdateParameters(PreparedStatement statement, Product entity) throws Exception {
		statement.setString(1, entity.getEntityName());
		statement.setString(2, entity.getDescription());
		statement.setDouble(3, entity.getPrice());
		statement.setInt(4, entity.getStockQuantity());
		statement.setString(5, entity.getEntityCode());
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE " + getTableName()
				+ " SET name = ?, description = ?, price = ?, stock_quantity = ? WHERE code = ?";
	}

	@Override
	protected String getSelectSql() {
		return "SELECT id, code, name, description, price, stock_quantity FROM " + getTableName();
	}

	@Override
	protected String getRegisterSql() {
		return "INSERT INTO " + getTableName()
				+ " (id, code, name, description, price, stock_quantity) VALUES (nextval('sq_product'), ?, ?, ?, ?, ?)";
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
