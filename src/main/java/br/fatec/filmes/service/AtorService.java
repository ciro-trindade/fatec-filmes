package br.fatec.filmes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.filmes.model.Ator;
import br.fatec.filmes.repository.AtorRepository;

@Service
public class AtorService implements ServiceInterface<Ator> {

	@Autowired
	private AtorRepository repo;
	
	@Override
	public Ator create(Ator obj) {
		repo.save(obj);
		return obj;
	}

	@Override
	public Ator findById(Long id) {
		Optional<Ator> _ator = repo.findById(id);		
		return _ator.orElse(null);
	}

	@Override
	public List<Ator> findAll() {
		return repo.findAll();
	}

	@Override
	public boolean update(Ator obj) {
		if (repo.existsById(obj.getId())) {
			repo.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
}
