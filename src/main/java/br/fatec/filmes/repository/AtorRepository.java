package br.fatec.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.filmes.model.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
	
}
