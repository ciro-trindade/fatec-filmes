package br.fatec.filmes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.filmes.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
	List<Filme> findByOrderByAno();
	
	@Query("select f from Filme f join f.atores a where a.id=?1")
	List<Filme> findByAtor(Long atorId);
	
	List<Filme> findByAno(Integer ano);
		
	List<Filme> findByAnoBetween(Integer from, Integer to);

}
