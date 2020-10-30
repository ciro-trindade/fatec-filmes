package br.fatec.filmes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.filmes.model.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
	
	List<Ator> findByOrderByNome();
	
	@Query("select a from Filme f join f.atores a where f.id=?1")
	List<Ator> findByFilme(Long filmeId);
	
	List<Ator> findByNacionalidadeId(Long naciolidadeId);
	
	@Query("select count(a) from Ator a where a.nacionalidade.id=?1")
	Long countAtorByNacionalidade(Long nacionalidadeId);
}
