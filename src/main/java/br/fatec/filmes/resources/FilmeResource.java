package br.fatec.filmes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.filmes.model.Filme;
import br.fatec.filmes.service.FilmeService;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeResource implements ResourceInterface<Filme> {

	@Autowired
	private FilmeService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<Filme>> get() {		
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Filme _filme = service.findById(id);
		if (_filme != null) {
			return ResponseEntity.ok(_filme);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	public ResponseEntity<Filme> post(@RequestBody Filme obj) {		
		return ResponseEntity.ok(service.create(obj));
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Filme obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping(value = "/ordenado")
	public ResponseEntity<List<Filme>> getOrdered() {
		return ResponseEntity.ok(service.findFilmeOrdenadoPorAno());
	}
	
	@GetMapping(value = "/ator/{id}")
	public ResponseEntity<List<Filme>> getByAtor(@PathVariable("id") Long atorId) {
		return ResponseEntity.ok(service.findByAtor(atorId));
	}
	
	@GetMapping(value = "/ano/{ano}")
	public ResponseEntity<List<Filme>> getByAno(@PathVariable("ano") Integer ano) {
		return ResponseEntity.ok(service.findByAno(ano));
	}
	
	@GetMapping(value = "/periodo/{from}/{to}")
	public ResponseEntity<List<Filme>> getByPeriodo(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {
		return ResponseEntity.ok(service.findByPeriodo(from, to));
	}
}
