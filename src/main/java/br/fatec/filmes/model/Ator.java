package br.fatec.filmes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "tb_ator")
@Entity
public class Ator extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nm_nome", length = 80)
	private String nome;
	
	@Column(name = "dt_nascimento")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date nascimento;
	
	@ManyToOne
	private Nacionalidade nacionalidade;

	public Ator() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
}
