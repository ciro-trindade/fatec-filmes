package br.fatec.filmes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.filmes.model.Filme;
import br.fatec.filmes.repositories.FilmeRepository;

@Service
public class FilmeService implements ServiceInterface<Filme> {

	@Autowired
	private FilmeRepository repo;
	
	@Override
	public Filme create(Filme obj) {
		repo.save(obj);
		return obj;
	}

	@Override
	public Filme findById(Long id) {
		Optional<Filme> _filme = repo.findById(id);		
		return _filme.orElse(null);
	}

	@Override
	public List<Filme> findAll() {
		return repo.findAll();
	}

	@Override
	public boolean update(Filme obj) {
		Optional<Filme> _filme = repo.findById(obj.getId());		
		if (_filme.isPresent()) {
			repo.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Filme> _filme = repo.findById(id);		
		if (_filme.isPresent()) {
			repo.delete(_filme.get());;
			return true;
		}
		return false;
	}
	
}
