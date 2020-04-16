package br.fepi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Formula")
public class Formula implements Serializable {

	private static final long serialVersionUID = 2L;

	private Long idFormula;
	private String descricao;

	public Formula(Long idFormula, String descricao) {
		super();
		this.idFormula = idFormula;
		this.descricao = descricao;
	}

	public Formula() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	@Column(name = "descricao", length = 80, nullable = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
