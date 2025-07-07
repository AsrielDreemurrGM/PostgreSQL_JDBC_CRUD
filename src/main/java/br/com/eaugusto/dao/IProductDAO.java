package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generics.IGenericDAO;
import br.com.eaugusto.domain.Product;

/**
 * DAO interface for {@link Product} entities.
 * 
 * <p>
 * Extends {@link IGenericDAO} to provide basic CRUD operations.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 7, 2025
 */
public interface IProductDAO extends IGenericDAO<Product> {

}
