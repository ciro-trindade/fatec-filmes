package br.fatec.filmes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.filmes.model.Ator;
import br.fatec.filmes.repositories.AtorRepository;

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
		Optional<Ator> _ator = repo.findById(obj.getId());		
		if (_ator.isPresent()) {
			repo.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Ator> _ator = repo.findById(id);		
		if (_ator.isPresent()) {
			repo.delete(_ator.get());;
			return true;
		}
		return false;
	}
	
	public List<Ator> listAtorOrdenadoPorNome() {
		return repo.findByOrderByNome();
	}
	
	public List<Ator> findByFilme(Long filmeId) {
		return repo.findByFilme(filmeId);
	}
	
	public List<Ator> findByNacionalidade(Long nacionalidadeId) {
		return repo.findByNacionalidadeId(nacionalidadeId);
	}
	
	public Long countAtorByNacionalidade(Long nacionalidadeId) {
		return repo.countAtorByNacionalidade(nacionalidadeId);
	}

}
