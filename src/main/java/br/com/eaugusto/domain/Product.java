package br.com.eaugusto.domain;

import br.com.eaugusto.annotations.Column;
import br.com.eaugusto.annotations.Table;

/**
 * Domain class representing a product entity.
 * <p>
 * Contains basic fields such as ID, name, code, and description, along with
 * their respective getters and setters. Implements {@link IPersistable} to
 * provide generic access to the product's identifying attributes.
 * </p>
 *
 * <p>
 * This class is used for data transfer between the application and the database
 * layer.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 6, 2025
 */
@Table("tb_product")
public class Product implements IPersistable {

	@Column("id")
	private Long id;

	@Column("code")
	private String code;

	@Column("name")
	private String name;

	@Column("description")
	private String description;

	@Column("price")
	private Double price;

	@Column("stock_quantity")
	private Integer stockQuantity;

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	@Override
	public String getEntityCode() {
		return code;
	}

	@Override
	public String getEntityName() {
		return name;
	}
}
