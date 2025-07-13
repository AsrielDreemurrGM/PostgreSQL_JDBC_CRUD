package br.com.eaugusto.domain;

/**
 * Represents an inventory transaction for a specific product sold to a client.
 * 
 * <p>
 * This class stores the data for each inventory sale, including the client and
 * product involved, the quantity sold, and the timestamp of the transaction.
 * </p>
 * 
 * <p>
 * The inventory is managed directly via DAO operations and is not part of the
 * generic annotation-based persistence.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 12, 2025
 */
public class Inventory {

	private Long id;
	private Long clientId;
	private Long productId;
	private Integer quantitySold;
	private java.sql.Timestamp saleDate;

	public Long getId() {
		return id;
	}

	public Long getClientId() {
		return clientId;
	}

	public Long getProductId() {
		return productId;
	}

	public Integer getQuantitySold() {
		return quantitySold;
	}

	public java.sql.Timestamp getSaleDate() {
		return saleDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setQuantitySold(Integer quantitySold) {
		this.quantitySold = quantitySold;
	}

	public void setSaleDate(java.sql.Timestamp saleDate) {
		this.saleDate = saleDate;
	}
}
