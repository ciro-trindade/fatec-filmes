package br.fatec.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.filmes.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
}
