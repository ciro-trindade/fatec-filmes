package br.fatec.filmes.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.fatec.filmes.model.Filme;
import br.fatec.filmes.service.FilmeService;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeController implements ControllerInterface<Filme> {

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

	@GetMapping("/page")
	public ResponseEntity<Page<Filme>> get(Pageable pageable) {		
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@GetMapping("/ator/{id}")
	public ResponseEntity<Page<Filme>> getByAtor(Pageable pageable, @PathVariable("id") Long atorId) {		
		return ResponseEntity.ok(service.findByAtor(pageable, atorId));
	}

	@GetMapping("/ano/{ano}")
	public ResponseEntity<Page<Filme>> getByAno(Pageable pageable, @PathVariable("ano") Integer ano) {		
		return ResponseEntity.ok(service.findByAno(pageable, ano));
	}

	@GetMapping("/count/{from}/{to}")
	public ResponseEntity<Long> countByPeriodo(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {		
		return ResponseEntity.ok(service.countByPeriodo(from, to));
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Filme> post(@RequestBody Filme obj) {	
		service.create(obj);
		URI uri = ServletUriComponentsBuilder
		        .fromCurrentRequest().path("/{id}")
		        .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
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
	
}
