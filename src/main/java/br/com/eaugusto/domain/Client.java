package br.com.eaugusto.domain;

/**
 * Domain class representing a client entity. Contains basic fields such as ID,
 * code, and name, along with their getters and setters. Implements
 * {@link IPersistable} for identification and for returning Client information.
 * 
 * This class is used for data transfer between the application and the database
 * layer.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
public class Client implements IPersistable {

	private Long id;

	private String code;

	private String name;

	public Long getId() {
		return id;
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

	@Override
	public String getEntityCode() {
		return code;
	}

	@Override
	public String getEntityName() {
		return name;
	}
}
