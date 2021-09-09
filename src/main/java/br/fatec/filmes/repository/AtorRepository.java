package br.fatec.filmes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.filmes.model.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {

	@Query("select a from Ator a order by a.nome")
	Page<Ator> findAllOrderByNome(Pageable pageable);
	
	@Query("select a from Filme f join f.atores a where f.id = ?1")
	Page<Ator> findByFilme(Pageable pageable, Long filmeId);
	
	@Query("select a from Ator a where a.nacionalidade.id = ?1")
	Page<Ator> findByNacionalidade(Pageable pageable, Long nacionalidadeId);
	
	@Query("select count(a) from Ator a where a.nacionalidade.id = ?1") 
	Long countByNacionalidade(Long nacionalidadeId);
}
