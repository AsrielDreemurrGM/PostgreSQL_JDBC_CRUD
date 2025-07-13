package br.com.eaugusto.domain;

import java.time.LocalDate;

import br.com.eaugusto.annotations.Column;
import br.com.eaugusto.annotations.Table;

/**
 * Domain class representing a client entity.
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

	@Column("cpf")
	private String cpf;

	@Column("phone")
	private String phone;

	@Column("address")
	private String address;

	@Column("address_number")
	private String addressNumber;

	@Column("city")
	private String city;

	@Column("state")
	private String state;

	@Column("birth_date")
	private LocalDate birthDate;

	public Long getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public LocalDate getBirthDate() {
		return birthDate;
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

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
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
