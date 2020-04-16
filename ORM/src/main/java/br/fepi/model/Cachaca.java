package br.fepi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Cachaca")
@PrimaryKeyJoinColumn(name = "Formula_Bebida")
public class Cachaca extends Bebida implements Serializable {

	private static final long serialVersionUID = 2L;
	private Date dataBarril;

	public Cachaca(String rotulo, Double volumeAlcoolico, BigDecimal preco, Date dataBarril) {
		super(rotulo, volumeAlcoolico, preco);
		this.dataBarril = dataBarril;
	}

	public Cachaca() {
	}

	@Column(name = "Data_barril", nullable = false)
	public Date getDataBarril() {
		return dataBarril;
	}

	public void setDataBarril(Date dataBarril) {
		this.dataBarril = dataBarril;
	}

	
}
