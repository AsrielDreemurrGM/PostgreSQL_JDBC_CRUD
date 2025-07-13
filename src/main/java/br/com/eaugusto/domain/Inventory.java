package br.com.eaugusto.domain;

/**
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
