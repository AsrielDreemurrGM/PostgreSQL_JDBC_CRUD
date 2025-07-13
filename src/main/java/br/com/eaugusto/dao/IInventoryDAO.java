package br.com.eaugusto.dao;

import java.util.List;

import br.com.eaugusto.domain.Inventory;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 12, 2025
 */
public interface IInventoryDAO {
	public Integer register(Inventory inventory);

	public List<Inventory> searchAll();

	public List<Inventory> searchByClient(Long clientId);

	public List<Inventory> searchByProduct(Long productId);

	public Integer deleteById(Long id);
}
