package br.com.eaugusto.dao;

import br.com.eaugusto.domain.Client;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public interface IClientDAO {

	public Integer register(Client client) throws Exception;

	/**
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public Client search(String code) throws Exception;

	/**
	 * @param clientDB
	 * @return
	 */
	public Integer delete(Client clientDB) throws Exception;
}
