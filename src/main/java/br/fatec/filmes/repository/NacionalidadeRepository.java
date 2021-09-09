package br.fatec.filmes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.filmes.model.Nacionalidade;

@Repository
public interface NacionalidadeRepository extends JpaRepository<Nacionalidade, Long> {
	
	@Query("select n from Nacionalidade n order by n.pais")
	Page<Nacionalidade> findAllOrderByPais(Pageable pageable);
	
	Page<Nacionalidade> findByPaisStartingWith(Pageable pageable, Character letra);
}
