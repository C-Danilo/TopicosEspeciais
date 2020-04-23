package br.fepi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carro")
public class Carro implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Long idCarro;	
	private String placaCarro;	
	private String modeloCarro;

	public Carro(String modeloCarro, String placaCarro) {
		this.placaCarro = placaCarro;
		this.modeloCarro = modeloCarro;
	}

	public Carro() {

	}
	
	@Id
	@GeneratedValue
	public Long getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(Long idCarro) {
		this.idCarro = idCarro;
	}
	
	
	@Column(length = 7, nullable = false)
	public String getPlacaCarro() {
		return placaCarro;
	}	

	public void setPlacaCarro(String placaCarro) {
		this.placaCarro = placaCarro;
	}

	@Column(length = 50, nullable = false)
	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	

}
