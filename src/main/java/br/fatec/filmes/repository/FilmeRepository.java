package br.fatec.filmes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.filmes.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
	@Query("select f from Filme f order by f.ano")
	Page<Filme> findAllOrderByAno(Pageable pageable);
	
	@Query("select f from Filme f join f.atores a where a.id = ?1")
	Page<Filme> findByAtor(Pageable pageable, Long atorId);
	
	Page<Filme> findByAno(Pageable pageable, Integer ano);
	
	@Query("select count(f) from Filme f where f.ano between ?1 and ?2")
	Long countByPeriodo(Integer from, Integer to);
}
