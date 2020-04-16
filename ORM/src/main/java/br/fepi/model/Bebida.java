package br.fepi.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Bebidas")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Bebida implements Serializable {

	private static final long serialVersionUID = 2L;

	private Long idBebida;
	private String rotulo;
	private Double volumeAlcoolico;
	private BigDecimal preco;

	private Formula formula;

	public Bebida(String rotulo, Double volumeAlcoolico, BigDecimal preco) {
		super();
		this.rotulo = rotulo;
		this.volumeAlcoolico = volumeAlcoolico;
		this.preco = preco;
	}

	public Bebida() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_bebida")
	public Long getIdBebida() {
		return idBebida;
	}

	public void setIdBebida(Long idBebida) {
		this.idBebida = idBebida;
	}

	@Column(name = "Rotulo", length = 40, nullable = true)
	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	@Column(name = "Vol_alcoolico", nullable = false)
	public Double getVolumeAlcoolico() {
		return volumeAlcoolico;
	}

	public void setVolumeAlcoolico(Double volumeAlcoolico) {
		this.volumeAlcoolico = volumeAlcoolico;
	}

	@Column(name = "Preco", nullable = false)
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToOne
	@JoinColumn(name = "Formula_Bebida")
	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}
	
	
}
