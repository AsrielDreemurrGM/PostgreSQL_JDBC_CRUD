package br.com.eaugusto.domain;

import br.com.eaugusto.annotations.Column;
import br.com.eaugusto.annotations.Table;

/**
 * Domain class representing a product entity.
 *
 * <p>
 * Fields are automatically mapped to database columns via annotations for
 * persistence. Implements {@link IPersistable} to provide generic
 * identification for DAO operations.
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

	@Column("category")
	private String category;

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

	public String getCategory() {
		return category;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setCategory(String category) {
		this.category = category;
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
