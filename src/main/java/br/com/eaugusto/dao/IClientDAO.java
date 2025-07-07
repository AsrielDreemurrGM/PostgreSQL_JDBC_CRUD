package br.com.eaugusto.dao;

import br.com.eaugusto.dao.generics.IGenericDAO;
import br.com.eaugusto.domain.Client;

/**
 * DAO interface for client-related database operations. Extends the standard
 * methods for registering, searching, updating and deleting clients from
 * {@link IGenericDAO}.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public interface IClientDAO extends IGenericDAO<Client> {

}
