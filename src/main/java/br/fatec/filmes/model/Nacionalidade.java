package br.fatec.filmes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tb_nacionalidade")
@Entity
public class Nacionalidade extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_pais", length = 50)
	private String pais;

	public Nacionalidade() {
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
		
}
