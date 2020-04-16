package br.fepi.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Cerveja")
@PrimaryKeyJoinColumn(name = "Formula_Bebida")
public class Cerveja extends Bebida implements Serializable {
	private static final long serialVersionUID = 2L;

	private TiposEnum tipo;

	public Cerveja(String rotulo, Double volumeAlcoolico, BigDecimal preco, TiposEnum tipo1) {
		super(rotulo, volumeAlcoolico, preco);
		this.tipo = tipo1;
	}

	public Cerveja() {
	}
	
	@Column(name = "Tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	public TiposEnum getTipo() {
		return tipo;
	}

	public void setTipo(TiposEnum tipo) {
		this.tipo = tipo;
	}

	
	
}