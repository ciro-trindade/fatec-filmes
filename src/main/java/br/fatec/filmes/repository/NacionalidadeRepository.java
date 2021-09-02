package br.fatec.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.filmes.model.Nacionalidade;

@Repository
public interface NacionalidadeRepository extends JpaRepository<Nacionalidade, Long> {
	
}
