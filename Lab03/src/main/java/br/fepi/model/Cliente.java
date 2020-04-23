package br.fepi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Long idCliente;	
	private String cpfCliente;	
	private String nomeCliente;
	
	public Cliente(String cpfCliente, String nomeCliente) {
		this.cpfCliente = cpfCliente;
		this.nomeCliente = nomeCliente;
	}
	
	public Cliente() {
		
	}
	
	@Id
	@GeneratedValue
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	
	@Column(length = 11, nullable = false, unique = true)
	public String getCpfCliente() {
		return cpfCliente;
	}
	

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	
	@Column(length = 50, nullable = false)
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	

}
