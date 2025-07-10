package br.com.eaugusto.domain;

import br.com.eaugusto.annotations.Column;
import br.com.eaugusto.annotations.Table;

/**
 * Domain class representing a client entity.
 * <p>
 * Contains basic fields such as ID, code, and name, along with their respective
 * getters and setters. Implements {@link IPersistable} to provide generic
 * access to the client's identifying attributes.
 * </p>
 *
 * <p>
 * This class is used for data transfer between the application and the database
 * layer.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 5, 2025
 */
@Table("tb_client")
public class Client implements IPersistable {

	@Column("id")
	private Long id;

	@Column("code")
	private String code;

	@Column("name")
	private String name;

	@Column("email")
	private String email;

	@Column("phone")
	private String phone;

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

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
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
