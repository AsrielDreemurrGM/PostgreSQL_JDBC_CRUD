package br.com.eaugusto.domain;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 6, 2025
 */
public class Product implements IPersistable {

	private Long id;

	private String name;

	private String code;

	private String description;

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
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

	@Override
	public String getEntityCode() {
		return code;
	}

	@Override
	public String getEntityName() {
		return name;
	}
}
