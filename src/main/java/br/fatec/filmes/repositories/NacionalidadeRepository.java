package br.fatec.filmes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.filmes.model.Nacionalidade;

@Repository
public interface NacionalidadeRepository extends JpaRepository<Nacionalidade, Long> {
	
	List<Nacionalidade> findByOrderByPais();
	
	List<Nacionalidade> findByPaisStartingWith(String letra);
}
